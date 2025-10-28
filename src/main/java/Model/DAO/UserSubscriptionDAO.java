/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.DTO.UserSubscriptionDTO;
import Model.DTO.UserDTO;
import Model.DTO.SubscriptionDTO;
import Util.HibernateUtil;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author phant
 */
public class UserSubscriptionDAO {

    // ?Thêm mới 1 user subscription
    public boolean addSubscription(UserSubscriptionDTO userSub) {
        boolean isSuccess = false;
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(userSub);
            tx.commit();
            isSuccess = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return isSuccess;
    }

    // Cập nhật subscription (ví dụ thay đổi ngày kết thúc, trạng thái, v.v.)
    public boolean updateSubscription(UserSubscriptionDTO userSub) {
        boolean isSuccess = false;
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(userSub);
            tx.commit();
            isSuccess = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return isSuccess;
    }


    // Lấy subscription theo ID
    public UserSubscriptionDTO getById(int subscriptionId) {
        UserSubscriptionDTO sub = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            sub = session.get(UserSubscriptionDTO.class, subscriptionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sub;
    }

    // Lấy danh sách subscription theo user
    public List<UserSubscriptionDTO> getByUser(UserDTO user) {
        List<UserSubscriptionDTO> list = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<UserSubscriptionDTO> query = session.createQuery(
                    "FROM tblUserSubscription WHERE user = :user", UserSubscriptionDTO.class);
            query.setParameter("user", user);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy danh sách subscription đang active
    public List<UserSubscriptionDTO> getActiveSubscriptions() {
        List<UserSubscriptionDTO> list = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<UserSubscriptionDTO> query = session.createQuery(
                    "FROM tblUserSubscription WHERE isActive = true", UserSubscriptionDTO.class);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy danh sách subscription đã hết hạn
    public List<UserSubscriptionDTO> getExpiredSubscriptions() {
        List<UserSubscriptionDTO> list = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<UserSubscriptionDTO> query = session.createQuery(
                    "FROM tblUserSubscription WHERE endDate < :now", UserSubscriptionDTO.class);
            query.setParameter("now", LocalDateTime.now());
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy tất cả subscriptions
    public List<UserSubscriptionDTO> getAllSubscriptions() {
        List<UserSubscriptionDTO> list = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            list = session.createQuery("FROM tblUserSubscription", UserSubscriptionDTO.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy subscription của 1 user theo plan cụ thể (nếu có)
    public UserSubscriptionDTO getByUserAndPlan(UserDTO user, SubscriptionDTO plan) {
        UserSubscriptionDTO result = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<UserSubscriptionDTO> query = session.createQuery(
                    "FROM tblUserSubscription WHERE user = :user AND subscription = :plan",
                    UserSubscriptionDTO.class);
            query.setParameter("user", user);
            query.setParameter("plan", plan);
            result = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    // Xóa mềm subscription

    public boolean softDeleteSubscription(int id) {
        boolean isSuccess = false;
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            UserSubscriptionDTO sub = session.get(UserSubscriptionDTO.class, id);
            if (sub != null && (sub.getIsDeleted() == null || !sub.getIsDeleted())) {
                sub.setIsDeleted(true);
                session.update(sub);
                isSuccess = true;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        }
        return isSuccess;
    }

    // Lấy danh sách chưa bị xóa
    public List<UserSubscriptionDTO> getActiveRecords() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM tblUserSubscription WHERE isDeleted = false", UserSubscriptionDTO.class).list();
        }
    }

}
