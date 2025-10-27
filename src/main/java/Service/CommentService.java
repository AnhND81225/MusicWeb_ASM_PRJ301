package Service;

import Model.DAO.CommentDAO;
import Model.DTO.CommentDTO;
import Model.DTO.SongDTO;
import Model.DTO.UserDTO;
import java.util.List;

public class CommentService {

    private CommentDAO commentDAO;

    public CommentService(CommentDAO commentDAO) {
        this.commentDAO = commentDAO;
    }

    // Thêm comment mới
    public boolean addComment(String content, UserDTO user, SongDTO song) {
        CommentDTO comment = new CommentDTO(content, user, song);
        return commentDAO.insert(comment) == 1;
    }

    // Xóa mềm comment
    public boolean deleteComment(CommentDTO comment) {
        return commentDAO.deleteSoft(comment) == 1;
    }

    // Lấy tất cả comment
    public List<CommentDTO> getAllComments() {
        return commentDAO.selectAll();
    }

    // Lấy tất cả comment hiển thị
    public List<CommentDTO> getAllVisibleComments() {
        return commentDAO.selectAllVisible();
    }

    // Lấy comment theo ID
    public CommentDTO getCommentById(Integer id) {
        return commentDAO.selectById(id);
    }
    
        // Lấy comment theo người dùng
    public List<CommentDTO> getCommentsByUser(UserDTO user) {
        return commentDAO.selectByUser(user);
    }

    // Lấy comment theo bài hát
    public List<CommentDTO> getCommentsBySong(SongDTO song) {
        return commentDAO.selectBySong(song);
    }
    
}
