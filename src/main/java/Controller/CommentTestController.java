package Controller;

import Model.DAO.CommentDAO;
import Model.DAO.NotificationDAO;
import Model.DTO.CommentDTO;
import Service.CommentService;
import Service.NotificationService;
import Util.HibernateUtil; // Giả định bạn có lớp này
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;

@WebServlet(name = "CommentTestController", urlPatterns = {"/test-comment"})
public class CommentTestController extends HttpServlet {

    // 1. Khai báo các Service cần thiết
    private CommentService commentService;
    private NotificationService notificationService;

    @Override
    public void init() throws ServletException {
        // Khởi tạo các Service và DAO
        SessionFactory factory = HibernateUtil.getSessionFactory();
        
        // Khởi tạo NotificationService (Cần thiết cho CommentService)
        NotificationDAO notificationDAO = new NotificationDAO(factory);
        this.notificationService = new NotificationService(notificationDAO);
        
        // Khởi tạo CommentService
        CommentDAO commentDAO = new CommentDAO(factory);
        this.commentService = new CommentService(commentDAO, this.notificationService);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 2. Thiết lập ID bài hát giả định
        // Chúng ta hardcode Song ID = 1 vì đây là môi trường test độc lập
        int testSongId = 1; 
        
        try {
            // 3. Lấy danh sách comment
            // Hàm này sẽ tự động lọc những comment có isHidden = false
            List<CommentDTO> comments = commentService.getCommentsBySongId(testSongId);
            
            // 4. Set dữ liệu vào Request Scope
            request.setAttribute("comments", comments);
            request.setAttribute("currentSongId", testSongId); // Để JSP lấy ID bài hát
            
            // 5. Forward đến trang JSP để hiển thị
            request.getRequestDispatcher("/song-detail.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error loading test page.");
        }
    }
    
    // Bạn không cần phương thức doPost() ở đây vì CommentController đã xử lý POST rồi.
}