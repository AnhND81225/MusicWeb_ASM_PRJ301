
package Model.DAO;

import Model.DTO.CommentDTO;
import Model.DTO.SongDTO;
import Model.DTO.UserDTO;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CommentDAO {

    private final SessionFactory factory;

    public CommentDAO(SessionFactory factory) {
        this.factory = factory;
    }

    public int insert(CommentDTO x) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            session.save(x);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return 0;
    }

    public int softDelete(int id) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            CommentDTO c = session.get(CommentDTO.class, id);

            if (c != null && !c.isHidden()) {
                c.setHidden(true);
                session.update(c);
                tx.commit();
                return 1;
            }
            tx.rollback();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return 0;
    }

    public CommentDTO selectById(int id) {
        try (Session session = factory.openSession()) {
            return session.get(CommentDTO.class, id);
        }
    }

    public List<CommentDTO> selectBySongId(int songId) {
        try (Session session = factory.openSession()) {
            return session.createQuery(
                "FROM CommentDTO WHERE song.songId = :songId AND isHidden = false",
                CommentDTO.class
            ).setParameter("songId", songId)
             .list();
        }
    }
}

