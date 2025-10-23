/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import model.Dao.SongDTO;
import Util.HibernateUtil;

public class SongDao {
    public List<Song> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.createQuery("FROM Song", Song.class).list();
    }

    public Song selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return session.get(Song.class, id);
    }

    public boolean insert(Song song) {
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

    public boolean delete(Song song) {
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
