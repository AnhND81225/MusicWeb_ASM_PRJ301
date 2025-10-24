/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DTO;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author phant
 */
@Entity
@Table(name = "PlaylistSong")
public class PlaylistSongDTO {
    
    @EmbeddedId
    private PlaylistSongID id;  // composite key (playlist_id + song_id)

    @ManyToOne
    @MapsId("playlistId")
    @JoinColumn(name = "playlist_id", referencedColumnName = "playlist_id")
    private PlaylistDTO playlist;

    @ManyToOne
    @MapsId("songId")
    @JoinColumn(name = "song_id")
    private SongDTO song;

    @Column(name = "position")
    private Integer position;
}
