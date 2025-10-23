/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import Model.DTO.AlbumDTO;
import Model.DTO.SongDTO;
import Util.HibernateUtil;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ASUS
 */
public class TestSong {

    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                try {
                    Transaction tr = session.beginTransaction();
                    // Example album and genre objects (replace with actual entities)
                    AlbumDTO album = new AlbumDTO();
                    album.setAlbumId(1);  // assuming an album with ID 1 exists

                    GenreDTO genre = new GenreDTO();
                    genre.setGenreId(2);  // assuming a genre with ID 2 exists

                    // Create a new song
                    SongDTO newSong = new SongDTO();
                    newSong.setTitle("Ocean of Dreams");
                    newSong.setFilePath("/music/ocean_of_dreams.mp3");
                    newSong.setDuration(245); // duration in seconds (4m 5s)
                    newSong.setAlbum(album);
                    newSong.setGenre(genre);
                    newSong.setCreatedAt(LocalDateTime.now());
                    newSong.setUpdatedAt(LocalDateTime.now());
                    tr.commit();
                } finally {
                    session.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
