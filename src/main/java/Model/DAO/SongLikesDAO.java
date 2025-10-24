package Model.DAO;

import Model.DTO.SongLikesDTO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class SongLikesDAO {

    private SessionFactory factory;

    public SongLikesDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public int insert(SongLikesDTO x) {
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

    public int update(SongLikesDTO x) {
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

    public int delete(SongLikesDTO x) {
        int kq = 0;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            SongLikesDTO a = session.get(SongLikesDTO.class, x.getLikeId());
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

    public List<SongLikesDTO> selectAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM SongLikesDTO", SongLikesDTO.class).list();
        }
    }

    public SongLikesDTO selectById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(SongLikesDTO.class, id);
        }
    }
}
