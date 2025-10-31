package Model.DAO;

import Model.DTO.DownloadHistoryDTO;
import Model.DTO.SongDTO;
import Model.DTO.UserDTO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DownloadHistoryDAO {

    private SessionFactory factory;

    public DownloadHistoryDAO(SessionFactory factory) {
        this.factory = factory;
    }

    //Thêm download mới
    public int insert(DownloadHistoryDTO x) {
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

    // Xóa mềm (đặt cờ isHidden = true)
    public int deleteSoft(Integer id) {
        int kq = 0;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            DownloadHistoryDTO a = session.get(DownloadHistoryDTO.class, id);
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

    // Lấy tất cả download chưa bị ẩn
    public List<DownloadHistoryDTO> selectAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                "FROM DownloadHistoryDTO WHERE isHidden = false",
                DownloadHistoryDTO.class
            ).list();
        }
    }

    // Lấy download theo ID
    public DownloadHistoryDTO selectById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(DownloadHistoryDTO.class, id);
        }
    }

    // Lấy tất cả download của 1 user (chưa bị ẩn)
    public List<DownloadHistoryDTO> selectByUser(UserDTO user) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                "FROM DownloadHistoryDTO WHERE user = :user AND isHidden = false",
                DownloadHistoryDTO.class
            ).setParameter("user", user).list();
        }
    }

    // Lấy tất cả download theo bài hát
    public List<DownloadHistoryDTO> selectBySong(SongDTO song) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                "FROM DownloadHistoryDTO WHERE song = :song AND isHidden = false",
                DownloadHistoryDTO.class
            ).setParameter("song", song).list();
        }
    }
}