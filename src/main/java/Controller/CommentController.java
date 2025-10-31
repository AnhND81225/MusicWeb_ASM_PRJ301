package Controller;

import Model.DAO.CommentDAO;
import Model.DTO.CommentDTO;
import Model.DTO.SongDTO;
import Model.DTO.UserDTO;
import Service.CommentService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import Util.HibernateUtil;

@WebServlet(name = "CommentController", urlPatterns = {"/comment"})
public class CommentController extends HttpServlet {

    private CommentService commentService;

    @Override
    public void init() throws ServletException {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        commentService = new CommentService(new CommentDAO(factory));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String content = request.getParameter("content");
            int userId = Integer.parseInt(request.getParameter("userId"));
            int songId = Integer.parseInt(request.getParameter("songId"));

            UserDTO user = new UserDTO();
            user.setUserID(userId);

            SongDTO song = new SongDTO();
            song.setSongId(songId);

            commentService.addComment(content, user, song);
            response.sendRedirect("song?action=details&id=" + songId);
            return;
        }

        if ("delete".equals(action)) {
            int commentId = Integer.parseInt(request.getParameter("commentId"));
            CommentDTO comment = commentService.getCommentById(commentId);

            if (comment != null) {
                commentService.deleteComment(comment);
                response.sendRedirect("song?action=details&id=" + comment.getSong().getSongId());
            }
        }
    }

}
