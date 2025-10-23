/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import Model.DTO.SongDTO;
import Util.HibernateUtil;

public class SongDao {
    public List<SongDTO> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("FROM Song", SongDTO.class).list();
    }

    public SongDTO selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(SongDTO.class, id);
    }

    public boolean insert(SongDTO song) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(song);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(SongDTO song) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(song);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }
}
