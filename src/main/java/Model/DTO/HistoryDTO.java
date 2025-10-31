/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DTO;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "History")
public class HistoryDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Integer historyId; // INT IDENTITY(1,1)

    @Column(name = "listened_at", insertable = false, updatable = false)
    private LocalDateTime listenedAt; // DATETIME DEFAULT GETDATE()

    // Cờ xóa mềm (ẩn lịch sử nghe nhạc thay vì xóa thật)
    @Column(name = "is_hidden", nullable = false)
    private boolean isHidden = false;

    // Khóa ngoại: Many-to-One tới Users
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserDTO user;

    // Khóa ngoại: Many-to-One tới Song
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", referencedColumnName = "song_id", nullable = false)
    private SongDTO song;

    // Constructors
    public HistoryDTO() {}

    public HistoryDTO(UserDTO user, SongDTO song) {
        this.user = user;
        this.song = song;
    }

    public Integer getHistoryId() {
        return historyId;
    }

    public void setHistoryId(Integer historyId) {
        this.historyId = historyId;
    }

    public LocalDateTime getListenedAt() {
        return listenedAt;
    }

    public void setListenedAt(LocalDateTime listenedAt) {
        this.listenedAt = listenedAt;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
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
}