/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DTO;

/**
 *
 * @author ASUS
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SongArtist")
public class SongArtistDTO implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "song_id", referencedColumnName = "song_id", nullable = false)
    private SongDTO song;

    @Id
    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "artist_id", nullable = false)
    private ArtistDTO artist;

    public SongArtistDTO() {
    }

    public SongArtistDTO(SongDTO song, ArtistDTO artist) {
        this.song = song;
        this.artist = artist;
    }

    public SongDTO getSong() {
        return song;
    }

    public void setSong(SongDTO song) {
        this.song = song;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }
}
