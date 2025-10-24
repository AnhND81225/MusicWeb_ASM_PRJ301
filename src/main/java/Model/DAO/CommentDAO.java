/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.DTO.CommentDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class CommentDAO {

    private EntityManagerFactory emf;

    public CommentDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public int insert(CommentDTO x) {
        EntityManager em = emf.createEntityManager();
        int kq = 0;
        try {
            em.getTransaction().begin();
            em.persist(x);
            em.getTransaction().commit();
            kq = 1;
        } finally {
            em.close();
        }
        return kq;
    }

    public int update(CommentDTO x) {
        EntityManager em = emf.createEntityManager();
        int kq = 0;
        try {
            em.getTransaction().begin();
            em.merge(x);
            em.getTransaction().commit();
            kq = 1;
        } finally {
            em.close();
        }
        return kq;
    }

    public int delete(CommentDTO x) {
        EntityManager em = emf.createEntityManager();
        int kq = 0;
        try {
            CommentDTO a = em.find(CommentDTO.class, x.getCommentId());
            if (a != null) {
                em.getTransaction().begin();
                em.remove(a);
                em.getTransaction().commit();
                kq = 1;
            }
        } finally {
            em.close();
        }
        return kq;
    }

    public List<CommentDTO> selectAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<CommentDTO> query = em.createQuery("SELECT c FROM CommentDTO c", CommentDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public CommentDTO selectById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(CommentDTO.class, id);
        } finally {
            em.close();
        }
    }
}

