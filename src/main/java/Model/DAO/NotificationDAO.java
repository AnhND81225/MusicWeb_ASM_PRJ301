/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.DTO.NotificationDTO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

public class NotificationDAO {

    private EntityManagerFactory emf;

    public NotificationDAO(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public int insert(NotificationDTO x) {
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

    public int update(NotificationDTO x) {
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

    public int delete(NotificationDTO x) {
        EntityManager em = emf.createEntityManager();
        int kq = 0;
        try {
            NotificationDTO a = em.find(NotificationDTO.class, x.getNotificationId());
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

    public List<NotificationDTO> selectAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<NotificationDTO> query = em.createQuery("SELECT n FROM NotificationDTO n", NotificationDTO.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public NotificationDTO selectById(Integer id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(NotificationDTO.class, id);
        } finally {
            em.close();
        }
    }
}

