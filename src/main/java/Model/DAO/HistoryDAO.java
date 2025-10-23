/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.DTO.HistoryDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class HistoryDAO {

    private EntityManagerFactory emf;

    public HistoryDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public int insert(HistoryDTO x) {
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

    public int update(HistoryDTO x) {
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

    public int delete(HistoryDTO x) {
        EntityManager em = emf.createEntityManager();
        int kq = 0;
        try {
            HistoryDTO a = em.find(HistoryDTO.class, x.getHistoryId());
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

    public List<HistoryDTO> selectAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<HistoryDTO> query = em.createQuery("SELECT h FROM HistoryDTO h", HistoryDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public HistoryDTO selectById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(HistoryDTO.class, id);
        } finally {
            em.close();
        }
    }
}

