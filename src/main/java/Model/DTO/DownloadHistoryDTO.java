package Model.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "DownloadHistory")
public class DownloadHistoryDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "download_id")
    private Integer downloadId;

    @Column(name = "downloaded_at", insertable = false, updatable = false, columnDefinition = "DATETIME DEFAULT GETDATE()")
    private LocalDateTime downloadedAt;

    // Khóa ngoại: Many-to-One tới tblUser
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserDTO user;

    // Khóa ngoại: Many-to-One tới Song
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", referencedColumnName = "song_id", nullable = false)
    private SongDTO song;

    @Column(name = "is_hidden", nullable = false)
    private boolean isHidden = false;

    public DownloadHistoryDTO() {}

    public DownloadHistoryDTO(UserDTO user, SongDTO song) {
        this.user = user;
        this.song = song;
    }

    public Integer getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Integer downloadId) {
        this.downloadId = downloadId;
    }

    public LocalDateTime getDownloadedAt() {
        return downloadedAt;
    }

    public void setDownloadedAt(LocalDateTime downloadedAt) {
        this.downloadedAt = downloadedAt;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public SongDTO getSong() {
        return song;
    }

    public void setSong(SongDTO song) {
        this.song = song;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }
}
