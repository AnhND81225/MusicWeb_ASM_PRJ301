package Test;

import org.hibernate.Session;
import Util.HibernateUtil;

public class HibernateTest {
    public static void main(String[] args) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("✅ Kết nối Hibernate thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi kết nối Hibernate:");
            e.printStackTrace();
        }
    }
}
