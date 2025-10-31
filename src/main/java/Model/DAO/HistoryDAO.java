package Model.DAO;

import Model.DTO.HistoryDTO;
import Model.DTO.SongDTO;
import Model.DTO.UserDTO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HistoryDAO {

    private SessionFactory factory;

    public HistoryDAO(SessionFactory factory) {
        this.factory = factory;
    }

    // Thêm mới lịch sử nghe nhạc
    public int insert(HistoryDTO x) {
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

    // Xóa mềm (ẩn bản ghi thay vì xóa thật)
    public int deleteSoft(Integer id) {
        int kq = 0;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            HistoryDTO a = session.get(HistoryDTO.class, id);
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

    //  Lấy tất cả bản ghi chưa bị xóa mềm
    public List<HistoryDTO> selectAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                "FROM HistoryDTO WHERE isHidden = false",
                HistoryDTO.class
            ).list();
        }
    }

    //  Lấy theo ID
    public HistoryDTO selectById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(HistoryDTO.class, id);
        }
    }

    //  Lấy lịch sử theo người dùng
    public List<HistoryDTO> selectByUser(UserDTO user) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                "FROM HistoryDTO WHERE user = :user AND isHidden = false",
                HistoryDTO.class
            ).setParameter("user", user)
             .list();
        }
    }

    // Lấy lịch sử theo bài hát
    public List<HistoryDTO> selectBySong(SongDTO song) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                "FROM HistoryDTO WHERE song = :song AND isHidden = false",
                HistoryDTO.class
            ).setParameter("song", song)
             .list();
        }
    }
}