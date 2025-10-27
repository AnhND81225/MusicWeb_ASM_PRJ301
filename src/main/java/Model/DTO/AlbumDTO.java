package Model.DTO;

import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "Album")
public class AlbumDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id")
    private int albumId;

    @Column(name = "title", nullable = false, length = 150)
    private String name;

    @Column(name = "release_date")
    private LocalDateTime releaseDate;

    @ManyToOne
    @JoinColumn(name = "artist_id", referencedColumnName = "artist_id", nullable = true)
    private ArtistDTO artist;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id", nullable = true)
    private GenreDTO genre;

    @Column(name = "cover_image", length = 255)
    private String coverImage;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @Column(name = "is_hidden")
    private boolean isHidden = false;

    public AlbumDTO() {}

      public AlbumDTO(int albumId, String name, LocalDateTime releaseDate, ArtistDTO artist, GenreDTO genre,
                    String coverImage, LocalDateTime createdDate, LocalDateTime updatedDate, boolean isHidden) {
        this.albumId = albumId;
        this.name = name;
        this.releaseDate = releaseDate;
        this.artist = artist;
        this.genre = genre;
        this.coverImage = coverImage;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.isHidden = isHidden;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public ArtistDTO getArtist() {
        return artist;
    }

    public void setArtist(ArtistDTO artist) {
        this.artist = artist;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
