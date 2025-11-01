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
    

    // BỔ SUNG HOẶC THAY THẾ PHƯƠNG THỨC addComment
public boolean addComment(String content, int userId, int songId, Integer parentCommentId) {
    
    // [Giả định: Cần UserDTO và SongDTO có ID]
    UserDTO user = new UserDTO();
    user.setUserID(userId);
    SongDTO song = new SongDTO();
    song.setSongId(songId); // ID của bài hát liên quan

    CommentDTO comment = new CommentDTO(content.trim(), user, song);
    
    // 1. Xử lý Reply (Thông báo cho người viết comment cha)
    if (parentCommentId != null) {
        CommentDTO parentComment = commentDAO.selectById(parentCommentId);
        
        if (parentComment != null) {
            comment.setParentComment(parentComment);
            
            // Lấy người nhận thông báo (Chủ của comment gốc)
            UserDTO recipient = parentComment.getUser();
            
            // 2. Gửi thông báo nếu người trả lời không phải là chính người nhận
            if (recipient != null && recipient.getUserID() != userId) { 
                
                String message = "User " + userId + " đã trả lời bình luận của bạn."; // Nội dung thông báo
                
                // Tạo và thêm Notification
                NotificationDTO notification = new NotificationDTO(message, recipient, song);
                notificationService.addNotification(notification);
            }
        }
    }
    
    comment.setHidden(false);
    // 3. Insert comment vào database
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
