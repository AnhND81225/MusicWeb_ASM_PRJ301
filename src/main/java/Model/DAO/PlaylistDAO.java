package Model.DAO;

import Model.DTO.PlaylistDTO;
import Model.DTO.UserDTO;
import Util.HibernateUtil;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class PlaylistDAO {

    // Thêm playlist mới
    public boolean addPlaylist(PlaylistDTO playlist) {
        boolean isSuccess = false;
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(playlist);
            tx.commit();
            isSuccess = true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return isSuccess;
    }

    // Cập nhật playlist
    public boolean updatePlaylist(PlaylistDTO playlist) {
        boolean isSuccess = false;
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            playlist.setUpdatedAt(LocalDateTime.now());
            session.update(playlist);
            tx.commit();
            isSuccess = true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return isSuccess;
    }

    // Xóa mềm playlist (ẩn playlist)
    public boolean hidePlaylist(int playlistId) {
        boolean isSuccess = false;
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            PlaylistDTO playlist = session.get(PlaylistDTO.class, playlistId);
            if (playlist != null) {
                // bạn cần thêm field hidden = false trong PlaylistDTO để soft delete
                playlist.setIsFavoriteList(false); // ví dụ: bỏ yêu thích
                playlist.setUpdatedAt(LocalDateTime.now());
                session.update(playlist);
                isSuccess = true;
            }
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
        return isSuccess;
    }

    // Lấy playlist theo ID
    public PlaylistDTO getById(int id) {
        PlaylistDTO playlist = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            playlist = session.get(PlaylistDTO.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playlist;
    }

    // Lấy tất cả playlist của 1 user
    public List<PlaylistDTO> getByUser(UserDTO user) {
        List<PlaylistDTO> list = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<PlaylistDTO> query = session.createQuery(
                "FROM tblPlayList WHERE user = :user", PlaylistDTO.class);
            query.setParameter("user", user);
            list = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // Lấy danh sách tất cả playlist
    public List<PlaylistDTO> getAll() {
        List<PlaylistDTO> list = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            list = session.createQuery("FROM tblPlayList", PlaylistDTO.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
