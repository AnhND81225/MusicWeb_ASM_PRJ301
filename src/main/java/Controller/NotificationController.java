package Controller;

import Model.DAO.NotificationDAO;
import Model.DTO.NotificationDTO;
import Service.NotificationService;
import Util.HibernateUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;

@WebServlet(name = "NotificationController", urlPatterns = {"/notification"})
public class NotificationController extends HttpServlet {

    private NotificationService notificationService;

    @Override
    public void init() throws ServletException {
        // Khởi tạo Service Layer
        SessionFactory factory = HibernateUtil.getSessionFactory();
        notificationService = new NotificationService(new NotificationDAO(factory));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            // Hiển thị danh sách thông báo
            handleListNotifications(request, response);
        } else {
             // Xử lý các GET action khác (nếu có)
             response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid GET action!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "read":
                    handleMarkAsRead(request, response);
                    break;
                case "delete":
                    // Tùy chọn: Xóa mềm thông báo
                    handleDeleteNotification(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid POST action!");
            }
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric parameter!");
        }
    }

    
    private void handleListNotifications(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        Integer currentUserId = 1; 
        
        if (currentUserId == null) {
            response.sendRedirect("login"); // Yêu cầu đăng nhập
            return;
        }

        // Lấy tất cả thông báo CHƯA bị ẩn của người dùng này
        request.setAttribute("notifications", 
                notificationService.getNotificationsByUserId(currentUserId));
        
        // Lấy số lượng thông báo CHƯA ĐỌC
        int unreadCount = notificationService.getUnreadNotificationsByUserId(currentUserId).size();
        request.setAttribute("unreadCount", unreadCount);

        // Chuyển tiếp đến trang JSP hiển thị danh sách
        request.getRequestDispatcher("/notification-list.jsp").forward(request, response);
    }

    
    private void handleMarkAsRead(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        Integer notificationId = Integer.parseInt(request.getParameter("id"));
        
        // 1. Đánh dấu đã đọc trong Database
        notificationService.markNotificationAsRead(notificationId);
        
        // 2. Chuyển hướng người dùng đến nội dung của thông báo
        // Lấy thông báo để có link redirect
        NotificationDTO notification = notificationService.getNotificationById(notificationId);
        
        if (notification != null && notification.getSong() != null) {
            // Giả định thông báo liên kết với một bài hát
            response.sendRedirect("song?action=details&id=" + notification.getSong().getSongId());
        } else {
            // Chuyển hướng về trang danh sách thông báo
            response.sendRedirect("notification"); 
        }
    }
    
    private void handleDeleteNotification(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        // Tùy chọn: Xử lý xóa mềm
        Integer notificationId = Integer.parseInt(request.getParameter("id"));
        
        // Giả định bạn có hàm selectById trong Service để lấy DTO cần xóa
        NotificationDTO notificationToDelete = notificationService.getNotificationById(notificationId);
        
        if (notificationToDelete != null) {
            notificationService.deleteNotification(notificationToDelete);
        }
        
        // Chuyển hướng về trang danh sách thông báo
        response.sendRedirect("notification");
    }
}