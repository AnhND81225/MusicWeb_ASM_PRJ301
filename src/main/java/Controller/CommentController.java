package Controller;

import Model.DAO.CommentDAO;
import Model.DAO.NotificationDAO;
import Model.DTO.CommentDTO;
import Model.DTO.SongDTO;
import Model.DTO.UserDTO;
import Service.CommentService;
import Service.NotificationService;
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
    private NotificationService notificationService;

    @Override
    public void init() throws ServletException {
        SessionFactory factory = HibernateUtil.getSessionFactory();
        // 1. Khởi tạo NotificationService
        this.notificationService = new NotificationService(new NotificationDAO(factory));

        // 2. Khởi tạo CommentService với dependency mới
        this.commentService = new CommentService(new CommentDAO(factory), this.notificationService);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "add":
                    handleAddComment(request, response);
                    break;
                case "delete":
                    handleDeleteComment(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action!");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric parameter!");
        }
    }

    // THAY THẾ HÀM NÀY
    private void handleAddComment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String content = request.getParameter("content");
        int userId = Integer.parseInt(request.getParameter("userId"));
        int songId = Integer.parseInt(request.getParameter("songId"));

        // BỔ SUNG: Lấy parentCommentId từ request (sẽ là null nếu là comment gốc)
        Integer parentCommentId = null;
        String parentIdParam = request.getParameter("parentCommentId");

        if (parentIdParam != null && !parentIdParam.trim().isEmpty()) {
            try {
                parentCommentId = Integer.parseInt(parentIdParam);
            } catch (NumberFormatException ignored) {
                // Có thể bỏ qua hoặc báo lỗi nếu tham số không hợp lệ
            }
        }

        if (content == null || content.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Comment cannot be empty!");
            return;
        }

        // GỌI PHƯƠNG THỨC addComment MỚI
        boolean success = commentService.addComment(content, userId, songId, parentCommentId);

        response.sendRedirect("test-comment");
    }

    private void handleDeleteComment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int commentId = Integer.parseInt(request.getParameter("commentId"));
        int songId = commentService.deleteComment(commentId);

        if (songId != -1) {
           response.sendRedirect("test-comment");
        } else {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Cannot delete this comment!");
        }
    }
}
