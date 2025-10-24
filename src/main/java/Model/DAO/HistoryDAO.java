package Model.DAO;

import Model.DTO.HistoryDTO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HistoryDAO {

    private SessionFactory factory;

    public HistoryDAO(SessionFactory factory) {
        this.factory = factory;
    }

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

    public int update(HistoryDTO x) {
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

    public int delete(HistoryDTO x) {
        int kq = 0;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            HistoryDTO a = session.get(HistoryDTO.class, x.getHistoryId());
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

    public List<HistoryDTO> selectAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM HistoryDTO", HistoryDTO.class).list();
        }
    }

    public HistoryDTO selectById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(HistoryDTO.class, id);
        }
    }
}