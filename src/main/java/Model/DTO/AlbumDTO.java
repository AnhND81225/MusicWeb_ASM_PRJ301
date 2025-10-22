/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DTO;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * /**
 *
 * @author ASUS
 */
@Entity
@Table(name = "Album")
public class AlbumDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private int albun_id;
    
    @Column(name = "title", nullable = false, length = 150)
    private String name;
    
    @Column(name ="release_date")
    private LocalDateTime release_date;
    
    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "artist_id", nullable = true)
    private ArtistDTO artist_id;
    
    @Column(name = "cover_image")
    private String cover_image;
    
    @Column(name ="created_date")
    private LocalDateTime created_date;
    
    @Column(name ="updated_date")
    private LocalDateTime updated_date;

    public AlbumDTO() {
    }

    public AlbumDTO(int albun_id, String name, LocalDateTime release_date, ArtistDTO artist_id, String cover_image, LocalDateTime created_date, LocalDateTime updated_date) {
        this.albun_id = albun_id;
        this.name = name;
        this.release_date = release_date;
        this.artist_id = artist_id;
        this.cover_image = cover_image;
        this.created_date = created_date;
        this.updated_date = updated_date;
    }

    public int getAlbun_id() {
        return albun_id;
    }

    public void setAlbun_id(int albun_id) {
        this.albun_id = albun_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDateTime release_date) {
        this.release_date = release_date;
    }

    public ArtistDTO getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(ArtistDTO artist_id) {
        this.artist_id = artist_id;
    }

    public String getCover_image() {
        return cover_image;
    }

    public void setCover_image(String cover_image) {
        this.cover_image = cover_image;
    }

    public LocalDateTime getCreated_date() {
        return created_date;
    }

    public void setCreated_date(LocalDateTime created_date) {
        this.created_date = created_date;
    }

    public LocalDateTime getUpdated_date() {
        return updated_date;
    }

    public void setUpdated_date(LocalDateTime updated_date) {
        this.updated_date = updated_date;
    }

}
