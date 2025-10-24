package Model.DAO;

import Model.DTO.CommentDTO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CommentDAO {

    private SessionFactory factory;

    public CommentDAO(SessionFactory factory) {
        this.factory = factory;
    }

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

    public int update(CommentDTO x) {
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

    public int delete(CommentDTO x) {
        int kq = 0;
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            CommentDTO a = session.get(CommentDTO.class, x.getCommentId());
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

    public List<CommentDTO> selectAll() {
        try (Session session = factory.openSession()) {
            return session.createQuery("FROM CommentDTO", CommentDTO.class).list();
        }
    }

    public CommentDTO selectById(Integer id) {
        try (Session session = factory.openSession()) {
            return session.get(CommentDTO.class, id);
        }
    }
}