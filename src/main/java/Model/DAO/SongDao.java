package Model.DAO;

import Model.DTO.SongDTO;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SongDao {

    // Lấy tất cả bài hát chưa bị ẩn
    public List<SongDTO> getAllSongs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM SongDTO WHERE isHidden = false", SongDTO.class).list();
        }
    }

    // Lấy bài hát theo ID
    public SongDTO getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(SongDTO.class, id);
        }
    }

    // Thêm bài hát mới
    public boolean insert(SongDTO song) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(song);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật bài hát
    public boolean update(SongDTO song) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(song);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Xóa mềm bài hát (ẩn đi)
    public boolean hide(int songId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            SongDTO song = session.get(SongDTO.class, songId);
            if (song == null) return false;

            tx = session.beginTransaction();
            song.setHidden(true);
            session.update(song);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Khôi phục bài hát đã bị ẩn
    public boolean restore(int songId) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            SongDTO song = session.get(SongDTO.class, songId);
            if (song == null) return false;

            tx = session.beginTransaction();
            song.setHidden(false);
            session.update(song);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Tìm kiếm bài hát theo từ khóa (tên bài hát hoặc tên nghệ sĩ)
    public List<SongDTO> searchByKeyword(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM SongDTO WHERE isHidden = false AND " +
                         "(lower(title) LIKE :kw OR lower(artist.name) LIKE :kw)";
            return session.createQuery(hql, SongDTO.class)
                    .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                    .list();
        }
    }

    // Lấy bài hát theo album
    public List<SongDTO> getSongsByAlbum(int albumId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM SongDTO WHERE isHidden = false AND album.albumId = :albumId", SongDTO.class)
                .setParameter("albumId", albumId)
                .list();
        }
    }

    // Lấy bài hát theo nghệ sĩ
    public List<SongDTO> getSongsByArtist(int artistId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM SongDTO WHERE isHidden = false AND artist.artistId = :artistId", SongDTO.class)
                .setParameter("artistId", artistId)
                .list();
        }
    }

    // Lấy bài hát theo thể loại
    public List<SongDTO> getSongsByGenre(int genreId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                "FROM SongDTO WHERE isHidden = false AND genre.genreId = :genreId", SongDTO.class)
                .setParameter("genreId", genreId)
                .list();
        }
    }

    // Lấy danh sách bài hát đã bị ẩn
    public List<SongDTO> getHiddenSongs() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM SongDTO WHERE isHidden = true", SongDTO.class).list();
        }
    }
}
