package Model.DAO;

import Model.DTO.AlbumDTO;
import Model.DTO.SongDTO;
import Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AlbumDao {

    // Lấy album theo ID
    public AlbumDTO getByID(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(AlbumDTO.class, id);
        }
    }

    // Tạo album mới
    public AlbumDTO createAlbum(AlbumDTO album) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.save(album);
            tx.commit();
            return album;
        }
    }

    // Cập nhật album
    public void updateAlbum(AlbumDTO album) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.update(album);
            tx.commit();
        }
    }

    // Xóa mềm album (ẩn album)
    public boolean hideAlbum(int albumId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            AlbumDTO album = session.get(AlbumDTO.class, albumId);
            if (album != null) {
                album.setHidden(true);
                session.update(album);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Khôi phục album đã bị ẩn
    public boolean restoreAlbum(int albumId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            AlbumDTO album = session.get(AlbumDTO.class, albumId);
            if (album != null) {
                album.setHidden(false);
                session.update(album);
                tx.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy tất cả album chưa bị ẩn
    public List<AlbumDTO> viewAllAlbums() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM AlbumDTO WHERE isHidden = false", AlbumDTO.class).list();
        }
    }

    // Lấy danh sách album đã bị ẩn
    public List<AlbumDTO> viewHiddenAlbums() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM AlbumDTO WHERE isHidden = true", AlbumDTO.class).list();
        }
    }

    // Tìm album theo tên
    public List<AlbumDTO> searchAlbumsByName(String keyword) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM AlbumDTO WHERE isHidden = false AND name LIKE :keyword", AlbumDTO.class)
                    .setParameter("keyword", "%" + keyword + "%")
                    .list();
        }
    }

    // Lấy album theo nghệ sĩ
    public List<AlbumDTO> getAlbumsByArtist(int artistId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM AlbumDTO WHERE isHidden = false AND artist.artistId = :artistId", AlbumDTO.class)
                    .setParameter("artistId", artistId)
                    .list();
        }
    }

    // Lấy album theo thể loại
    public List<AlbumDTO> getAlbumsByGenre(int genreId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                    "FROM AlbumDTO WHERE isHidden = false AND genre.genreId = :genreId", AlbumDTO.class)
                    .setParameter("genreId", genreId)
                    .list();
        }
    }

    // Thêm bài hát vào album
    public SongDTO addSongToAlbum(SongDTO song, int albumId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            AlbumDTO album = session.get(AlbumDTO.class, albumId);
            if (album == null) {
                throw new RuntimeException("Album ID not found: " + albumId);
            }
            song.setAlbum(album);
            session.save(song);
            tx.commit();
            return song;
        }
    }
}
