/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.DTO.SongLikesDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class SongLikesDAO {

    private EntityManagerFactory emf;

    public SongLikesDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public int insert(SongLikesDTO x) {
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

    public int update(SongLikesDTO x) {
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

    public int delete(SongLikesDTO x) {
        EntityManager em = emf.createEntityManager();
        int kq = 0;
        try {
            SongLikesDTO a = em.find(SongLikesDTO.class, x.getLikeId());
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

    public List<SongLikesDTO> selectAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<SongLikesDTO> query = em.createQuery("SELECT s FROM SongLikesDTO s", SongLikesDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public SongLikesDTO selectById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(SongLikesDTO.class, id);
        } finally {
            em.close();
        }
    }
}
