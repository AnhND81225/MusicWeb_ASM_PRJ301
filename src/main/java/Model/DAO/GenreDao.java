/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.DTO.GenreDTO;
import Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ASUS
 */
public class GenreDao {

    public boolean update(GenreDTO genre) {
        Transaction tx = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(genre);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public boolean insert(GenreDTO genre) {
        Transaction tx = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(genre);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    public List<GenreDTO> getAllGenre() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("From GenreDTO WHERE isHidden = false", GenreDTO.class).list();
        }
    }

    public List<GenreDTO> getByName(String keyword) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "From GenreDTO where name like :kw";
            return session.createQuery(hql, GenreDTO.class).setParameter("kw", "%" + keyword.toLowerCase() + "%")
                    .list();
        }
    }

    public GenreDTO getById(int genreId) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(GenreDTO.class, genreId);
        }
    }

    public List<GenreDTO> getFeaturedGenres() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM GenreDTO WHERE isFeatured = true";
            return session.createQuery(hql, GenreDTO.class).list();
        }
    }

}
