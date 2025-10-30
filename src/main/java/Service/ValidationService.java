/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DTO.*;

import java.util.List;

/**
 *
 * @author ASUS
 */
public class ValidationService {

    public boolean isValid(Object dto) {
        if (dto == null) {
            return false;
        }

        if (dto instanceof SongDTO) {
            return validateSong((SongDTO) dto);
        }
        if (dto instanceof ArtistDTO) {
            return validateArtist((ArtistDTO) dto);
        }
        if (dto instanceof AlbumDTO) {
            return validateAlbum((AlbumDTO) dto);
        }
        if (dto instanceof GenreDTO) {
            return validateGenre((GenreDTO) dto);
        }

        return false; 
    }

    private boolean validateSong(SongDTO song) {
        return notEmpty(song.getTitle())
                && notEmpty(song.getFilePath())
                && song.getDuration() > 0
                && song.getArtists() != null && !song.getArtists().isEmpty();
    }

    private boolean validateArtist(ArtistDTO artist) {
        return notEmpty(artist.getName())
                && notEmpty(artist.getBio());
    }

    private boolean validateAlbum(AlbumDTO album) {
        return notEmpty(album.getName())
                && album.getReleaseDate() != null
                && album.getReleaseDate().getYear() + 1900 > 1900;
    }

    private boolean validateGenre(GenreDTO genre) {
        return notEmpty(genre.getName());
    }

    private boolean notEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }
}
