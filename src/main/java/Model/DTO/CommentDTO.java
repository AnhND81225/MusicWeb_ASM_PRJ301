/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.DTO;

/**
 *
 * @author Villain
 */
 import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name = "Comment")
public class CommentDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Integer commentId; // INT IDENTITY(1,1)

    @Column(name = "content", columnDefinition = "NVARCHAR(MAX)", nullable = false)
    private String content;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt; // DATETIME DEFAULT GETDATE()

    // Khóa ngoại: Many-to-One tới Users
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserDTO user; // Bạn cần có class Users

    // Khóa ngoại: Many-to-One tới Song
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "song_id", referencedColumnName = "song_id", nullable = false)
    private SongDTO song; // Bạn cần có class Song

    // Constructors
    public CommentDTO() {}
    
    public CommentDTO(String content, UserDTO user, SongDTO song) {
        this.content = content;
        this.user = user;
        this.song = song;
    }

    // Getters và Setters (Bạn nên tự thêm vào cho đầy đủ, tôi chỉ liệt kê một vài ví dụ)
    public Integer getCommentId() { return commentId; }
    public void setCommentId(Integer commentId) { this.commentId = commentId; }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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

