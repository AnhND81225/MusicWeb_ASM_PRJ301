package Test;

import Model.DTO.SongDTO;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        SongDTO song = new SongDTO();
        song.setTitle("Test Song");
        song.setFilePath("test.mp3");
        song.setDuration(180);
        session.save(song);

        tx.commit();
        session.close();
        HibernateUtil.shutdown();
    }
}
