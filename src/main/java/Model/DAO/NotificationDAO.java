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

    public int insert(NotificationDTO x) {
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

    public int update(NotificationDTO x) {
        int kq = 0;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.update(x);
            tx.commit();
            kq = 1;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return kq;
    }

    public int delete(NotificationDTO x) {
        int kq = 0;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            NotificationDTO a = session.get(NotificationDTO.class, x.getNotificationId());
            if (a != null) {
                session.delete(a);
                kq = 1;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return kq;
    }

    public List<NotificationDTO> selectAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM NotificationDTO", NotificationDTO.class).list();
        }
    }

    public NotificationDTO selectById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(NotificationDTO.class, id);
        }
    }
}
