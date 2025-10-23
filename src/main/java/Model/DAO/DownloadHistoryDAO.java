/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.DTO.DownloadHistoryDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class DownloadHistoryDAO {

    private EntityManagerFactory emf;

    public DownloadHistoryDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public int insert(DownloadHistoryDTO x) {
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

    public int update(DownloadHistoryDTO x) {
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

    public int delete(DownloadHistoryDTO x) {
        EntityManager em = emf.createEntityManager();
        int kq = 0;
        try {
            DownloadHistoryDTO a = em.find(DownloadHistoryDTO.class, x.getDownloadId());
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

    public List<DownloadHistoryDTO> selectAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<DownloadHistoryDTO> query = em.createQuery("SELECT d FROM DownloadHistoryDTO d", DownloadHistoryDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public DownloadHistoryDTO selectById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(DownloadHistoryDTO.class, id);
        } finally {
            em.close();
        }
    }
}
