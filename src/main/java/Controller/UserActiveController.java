/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.DTO.*;
import Model.DAO.*;
import Service.*;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
/**
 *
 * @author Villain
 */
@WebServlet(name = "UserActiveController", urlPatterns = {"/UserActiveController"})
public class UserActiveController extends HttpServlet {
    private SessionFactory factory;

    private CommentService commentService;
    private HistoryService historyService;
    private DownloadHistoryService downloadService;
    private NotificationService notificationService;
    private SongLikesService songLikesService;
    
    @Override
    public void init() throws ServletException {
        factory = new Configuration().configure().buildSessionFactory();

        commentService = new CommentService(new CommentDAO(factory));
        historyService = new HistoryService(new HistoryDAO(factory));
        downloadService = new DownloadHistoryService(new DownloadHistoryDAO(factory));
        notificationService = new NotificationService(new NotificationDAO(factory));
        songLikesService = new SongLikesService(new SongLikesDAO(factory));
    }
    
      @Override
    public void destroy() {
        factory.close();
    }
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("txtAction");
        HttpSession session = request.getSession();
        UserDTO currentUser = (UserDTO) session.getAttribute("user");

        if (action == null || currentUser == null) {
            response.sendRedirect("HomePage.jsp");
            return;
        }
         switch (action) {
            case "comment":
                handleComment(request, response, currentUser);
                break;
            case "history":
                handleHistory(request, response, currentUser);
                break;
            case "downloadHistory":
                handleDownloadHistory(request, response, currentUser);
                break;
            case "notification":
                handleNotification(request, response, currentUser);
                break;
            case "songLikes":
                handleSongLikes(request, response, currentUser);
                break;
            default:
                response.sendRedirect("HomePage.jsp");
        }
    }
    private void handleComment(HttpServletRequest request, HttpServletResponse response, UserDTO user)
            throws ServletException, IOException {
        String songIdStr = request.getParameter("songId");
        String content = request.getParameter("content");

        if (songIdStr != null && content != null) {
            SongDTO song = new SongDTO();
            song.setSongId(Integer.parseInt(songIdStr));

            boolean success = commentService.addComment(content, user, song);
            request.setAttribute("message", success ? "Bình luận thành công!" : "Thất bại khi bình luận.");
        }

        List<CommentDTO> comments = commentService.getCommentsByUser(user);
        request.setAttribute("commentList", comments);
        request.getRequestDispatcher("Comment.jsp").forward(request, response);
    }
    private void handleHistory(HttpServletRequest request, HttpServletResponse response, UserDTO user)
            throws ServletException, IOException {
        List<HistoryDTO> historyList = historyService.getHistoryByUser(user);
        request.setAttribute("historyList", historyList);
        request.getRequestDispatcher("History.jsp").forward(request, response);
    }
     private void handleDownloadHistory(HttpServletRequest request, HttpServletResponse response, UserDTO user)
            throws ServletException, IOException {
        List<DownloadHistoryDTO> downloads = downloadService.getDownloadsByUser(user);
        request.setAttribute("downloadList", downloads);
        request.getRequestDispatcher("DownloadHistory.jsp").forward(request, response);
    }

    private void handleNotification(HttpServletRequest request, HttpServletResponse response, UserDTO user)
            throws ServletException, IOException {
        List<NotificationDTO> notifications = notificationService.getNotificationsByUserId(user.getUserID());
        request.setAttribute("notificationList", notifications);
        request.getRequestDispatcher("Notification.jsp").forward(request, response);
    }

    private void handleSongLikes(HttpServletRequest request, HttpServletResponse response, UserDTO user)
            throws ServletException, IOException {
        String songIdStr = request.getParameter("songId");
        if (songIdStr != null) {
            SongDTO song = new SongDTO();
            song.setSongId(Integer.parseInt(songIdStr));

            SongLikesDTO existingLike = songLikesService.getLikeByUserAndSong(user, song);
            boolean success;

            if (existingLike == null) {
                // Chưa like → thêm mới
                success = songLikesService.addLike(new SongLikesDTO(user, song));
                request.setAttribute("message", success ? "Đã like bài hát!" : "Không thể like.");
            } else {
                // Đã like → xóa mềm
                success = songLikesService.removeLike(existingLike);
                request.setAttribute("message", success ? "Đã bỏ like!" : "Không thể bỏ like.");
            }
        }

        List<SongLikesDTO> likes = songLikesService.getAllVisibleLikes();
        request.setAttribute("likeList", likes);
        request.getRequestDispatcher("SongLikes.jsp").forward(request, response);
    }

    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
