package Model.DAO;

import Model.DTO.DownloadHistoryDTO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DownloadHistoryDAO {

    private SessionFactory factory;

    public DownloadHistoryDAO(SessionFactory factory) {
        this.factory = factory;
    }

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

    public int update(DownloadHistoryDTO x) {
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

    public int delete(DownloadHistoryDTO x) {
        int kq = 0;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            DownloadHistoryDTO a = session.get(DownloadHistoryDTO.class, x.getDownloadId());
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

    public List<DownloadHistoryDTO> selectAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM DownloadHistoryDTO", DownloadHistoryDTO.class).list();
        }
    }

    public DownloadHistoryDTO selectById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(DownloadHistoryDTO.class, id);
        }
    }
}
