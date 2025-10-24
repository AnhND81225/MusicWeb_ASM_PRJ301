/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DTO;

import java.util.Objects;

/**
 *
 * @author phant
 */
public class PlaylistSongID {
    private Integer playlistId;
    private Integer songId;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.playlistId);
        hash = 97 * hash + Objects.hashCode(this.songId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PlaylistSongID other = (PlaylistSongID) obj;
        if (!Objects.equals(this.playlistId, other.playlistId)) {
            return false;
        }
        return Objects.equals(this.songId, other.songId);
    }
    
}
