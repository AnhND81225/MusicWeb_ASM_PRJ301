package Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Tạo đối tượng SessionFactory từ file cấu hình hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            System.err.println("Khởi tạo SessionFactory thất bại: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
