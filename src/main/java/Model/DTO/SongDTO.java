package Model.DTO;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Song")
public class SongDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "song_id")
    private int songId;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "file_path", nullable = false, unique = true, length = 255)
    private String filePath;

    @Column(name = "duration")
    private int duration;

    @ManyToOne
    @JoinColumn(name = "album_id", referencedColumnName = "album_id")
    private AlbumDTO album;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    private GenreDTO genre;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_hidden")
    private boolean isHidden = false;

    @Column(name = "is_featured")
    private boolean isFeatured = false;

    @Column(name = "play_count")
    private int playCount = 0;

    @ManyToMany
    @JoinTable(
            name = "SongArtist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<ArtistDTO> artists;

    public SongDTO() {
    }

    // Constructor đầy đủ
    public SongDTO(int songId, String title, String filePath, int duration, AlbumDTO album, GenreDTO genre,
            LocalDateTime createdAt, LocalDateTime updatedAt, boolean isHidden,
            boolean isFeatured, int playCount, List<ArtistDTO> artists) {
        this.songId = songId;
        this.title = title;
        this.filePath = filePath;
        this.duration = duration;
        this.album = album;
        this.genre = genre;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isHidden = isHidden;
        this.isFeatured = isFeatured;
        this.playCount = playCount;
        this.artists = artists;
    }

    // Getters và setters
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public AlbumDTO getAlbum() {
        return album;
    }

    public void setAlbum(AlbumDTO album) {
        this.album = album;
    }

    public GenreDTO getGenre() {
        return genre;
    }

    public void setGenre(GenreDTO genre) {
        this.genre = genre;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public List<ArtistDTO> getArtists() {
        return artists;
    }

    public void setArtists(List<ArtistDTO> artists) {
        this.artists = artists;
    }
}
