
package Model.DAO;

import Model.DTO.CommentDTO;
import Model.DTO.SongDTO;
import Model.DTO.UserDTO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CommentDAO {

    private SessionFactory factory;

    public CommentDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public CommentDAO() {
    }

    // Thêm comment
    public int insert(CommentDTO x) {
        int kq = 0;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.save(x);
            tx.commit();
            kq = 1;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return kq;
    }

    // Xóa mềm comment
    public int deleteSoft(CommentDTO x) {
        int kq = 0;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            CommentDTO a = session.get(CommentDTO.class, x.getCommentId());
            if (a != null && !a.isHidden()) {
                a.setHidden(true);
                session.update(a);
                kq = 1;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return kq;
    }

    // Lấy tất cả comment
    public List<CommentDTO> selectAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM CommentDTO", CommentDTO.class).list();
        }
    }

    // Lấy tất cả comment hiển thị (không bị ẩn)
    public List<CommentDTO> selectAllVisible() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM CommentDTO WHERE isHidden = false", CommentDTO.class).list();
        }
    }

    // Lấy comment theo ID
    public CommentDTO selectById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(CommentDTO.class, id);
        }
    }

    // Lấy comment theo người dùng
    public List<CommentDTO> selectByUser(UserDTO user) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                "FROM CommentDTO WHERE user = :user AND isHidden = false", CommentDTO.class)
                .setParameter("user", user)
                .list();
        }
    }

    // Lấy comment theo bài hát
    public List<CommentDTO> selectBySong(SongDTO song) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                "FROM CommentDTO WHERE song = :song AND isHidden = false", CommentDTO.class)
                .setParameter("song", song)
                .list();
        }
    }
}
