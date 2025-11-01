package Service;

import Model.DAO.CommentDAO;
import Model.DTO.CommentDTO;
import Model.DTO.NotificationDTO;
import Model.DTO.SongDTO;
import Model.DTO.UserDTO;
import java.util.List;

public class CommentService {

    private final CommentDAO commentDAO;
    
    
    // THÊM: Cần NotificationService để tạo thông báo
    private final NotificationService notificationService; 

    // Cập nhật Constructor
    public CommentService(CommentDAO commentDAO, NotificationService notificationService) {
        this.commentDAO = commentDAO;
        this.notificationService = notificationService;
    }
    

    public boolean addComment(String content, int userId, int songId, Integer parentCommentId) {
    
    // Tạo DTO cho khóa ngoại chỉ với ID
    UserDTO user = new UserDTO();
    user.setUserID(userId);
    SongDTO song = new SongDTO();
    song.setSongId(songId); 

    CommentDTO comment = new CommentDTO(content.trim(), user, song);
    
    // 1. Xử lý Reply (Thông báo cho người viết comment cha)
    if (parentCommentId != null) {
        CommentDTO parentComment = commentDAO.selectById(parentCommentId); 
        
        if (parentComment != null) {
            
            // ************ SỬA LỖI QUAN TRỌNG NHẤT ************
            // TẠO DTO GIẢ CHỈ CÓ ID CHO PARENT COMMENT ĐỂ TRÁNH LỖI DETACHED
            CommentDTO parentCommentRef = new CommentDTO();
            parentCommentRef.setCommentId(parentCommentId);
            comment.setParentComment(parentCommentRef);
            // ***************************************************
            
            // Lấy ID người nhận (ĐÃ ĐƯỢC LOAD EAGERLY ở DAO)
            int recipientId = parentComment.getUser().getUserID();
            
            // 2. Gửi thông báo nếu người trả lời không phải là chính người nhận
            if (recipientId != userId) {
                String message = "User " + userId + " đã trả lời bình luận của bạn."; 
                
                UserDTO recipient = new UserDTO();
                recipient.setUserID(recipientId);
                
                // Tạo và thêm Notification (Sẽ chạy Transaction riêng)
                NotificationDTO notification = new NotificationDTO(message, recipient, song);
                notificationService.addNotification(notification);
            }
        }
    }
    
    comment.setHidden(false);
    // 3. Insert comment vào database (Transaction riêng)
    // Lệnh này giờ sẽ an toàn hơn vì nó không cố gắng lưu DTO Detached
    return commentDAO.insert(comment) == 1; 
}

    public int deleteComment(int commentId) {
        CommentDTO comment = commentDAO.selectById(commentId);

        if (comment == null || comment.isHidden()) return -1;

        if (commentDAO.softDelete(commentId) == 1) {
            return comment.getSong().getSongId();
        }
        return -1;
    }

    public List<CommentDTO> getCommentsBySongId(int songId) {
        return commentDAO.selectBySongId(songId);
    }
}
