package Model.DAO;

import Model.DTO.NotificationDTO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class NotificationDAO {

    private SessionFactory factory;

    public NotificationDAO(SessionFactory factory) {
        this.factory = factory;
    }

    // Thêm thông báo
    public int insert(NotificationDTO x) {
        int kq = 0;
        Transaction tx = null;
        try ( Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.save(x);
            tx.commit();
            kq = 1;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return kq;
    }

    // Xóa mềm thông báo
    public int deleteSoft(NotificationDTO x) {
        int kq = 0;
        Transaction tx = null;
        try ( Session session = factory.openSession()) {
            tx = session.beginTransaction();
            NotificationDTO a = session.get(NotificationDTO.class, x.getNotificationId());
            if (a != null && !a.getIsHidden()) {
                a.setIsHidden(true);
                session.update(a);
                kq = 1;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return kq;
    }

    // Lấy tất cả thông báo
    public List<NotificationDTO> selectAll() {
        try ( Session session = factory.openSession()) {
            return session.createQuery("FROM NotificationDTO", NotificationDTO.class).list();
        }
    }

    // Lấy tất cả thông báo hiển thị (không bị ẩn)
    public List<NotificationDTO> selectAllVisible() {
        try ( Session session = factory.openSession()) {
            return session.createQuery("FROM NotificationDTO WHERE isHidden = false", NotificationDTO.class).list();
        }
    }

    // Lấy thông báo theo ID
    public NotificationDTO selectById(Integer id) {
        try ( Session session = factory.openSession()) {
            return session.get(NotificationDTO.class, id);
        }
    }
    // Lấy thông báo theo user_id

    public List<NotificationDTO> selectByUserId(Integer userId) {
        try ( Session session = factory.openSession()) {
            return session.createQuery(
                    "FROM NotificationDTO WHERE user.userId = :userId AND isHidden = false", NotificationDTO.class)
                    .setParameter("userId", userId)
                    .list();
        }
    }

// Lấy thông báo theo song_id
    public List<NotificationDTO> selectBySongId(Integer songId) {
        try ( Session session = factory.openSession()) {
            return session.createQuery(
                    "FROM NotificationDTO WHERE song.songId = :songId AND isHidden = false", NotificationDTO.class)
                    .setParameter("songId", songId)
                    .list();
        }
    }
}
