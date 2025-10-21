/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ASUS
 */

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "SongArtist")
public class SongArtist implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "song_id", referencedColumnName = "song_id", nullable = false)
    private Song song;

    @Id
    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "artist_id", nullable = false)
    private Artist artist;

    public SongArtist() {
    }

    public SongArtist(Song song, Artist artist) {
        this.song = song;
        this.artist = artist;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
