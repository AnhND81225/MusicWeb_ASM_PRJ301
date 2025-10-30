/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DAO;

import Model.DTO.ArtistDTO;
import Model.DTO.SongDTO;
import Util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author ASUS
 */
public class ArtistDao {

    public boolean insert(ArtistDTO artist) {
        Transaction tx = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(artist);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin nghệ sĩ
    public boolean update(ArtistDTO artist) {
        Transaction tx = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(artist);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Lấy tất cả nghệ sĩ chưa bị ẩn
    public List<ArtistDTO> getAllArtists() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM ArtistDTO WHERE isHidden = false", ArtistDTO.class).list();
        }
    }

    // Lấy nghệ sĩ theo ID
    public ArtistDTO getById(int artistId) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(ArtistDTO.class, artistId);
        }
    }

    // Ẩn nghệ sĩ (xóa mềm)
    public boolean hideArtist(int artistId) {
        Transaction tx = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            ArtistDTO artist = session.get(ArtistDTO.class, artistId);
            if (artist == null) {
                return false;
            }

            tx = session.beginTransaction();
            artist.setHidden(true);
            session.update(artist);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Khôi phục nghệ sĩ đã bị ẩn
    public boolean restoreArtist(int artistId) {
        Transaction tx = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            ArtistDTO artist = session.get(ArtistDTO.class, artistId);
            if (artist == null) {
                return false;
            }

            tx = session.beginTransaction();
            artist.setHidden(false);
            session.update(artist);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    // Tìm kiếm nghệ sĩ theo tên
    public List<ArtistDTO> searchByName(String keyword) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM ArtistDTO WHERE isHidden = false AND lower(name) LIKE :kw";
            return session.createQuery(hql, ArtistDTO.class)
                    .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                    .list();
        }
    }

    // Lấy danh sách nghệ sĩ đã bị ẩn
    public List<ArtistDTO> getHiddenArtists() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM ArtistDTO WHERE isHidden = true", ArtistDTO.class).list();
        }
    }

    //Lấy nghệ sĩ nổi bật (isPopular = true)
    public List<ArtistDTO> getPopularArtists() {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM ArtistDTO WHERE isHidden = false AND isPopular = true";
            return session.createQuery(hql, ArtistDTO.class).list();
        }
    }

    //Lấy top nghệ sĩ theo số lượng người theo dõi (followerCount)
    public List<ArtistDTO> getTopArtists(int limit) {
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM ArtistDTO WHERE isHidden = false ORDER BY followerCount DESC";
            return session.createQuery(hql, ArtistDTO.class)
                    .setMaxResults(limit)
                    .list();
        }
    }

    //Tăng số lượng người theo dõi cho nghệ sĩ
    public boolean increaseFollowerCount(int artistId) {
        Transaction tx = null;
        try ( Session session = HibernateUtil.getSessionFactory().openSession()) {
            ArtistDTO artist = session.get(ArtistDTO.class, artistId);
            if (artist == null || artist.isHidden()) {
                return false;
            }
            tx = session.beginTransaction();
            artist.setFollowerCount(artist.getFollowerCount() + 1);
            session.update(artist);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

}
