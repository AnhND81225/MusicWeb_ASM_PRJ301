package Model.DTO;

import javax.persistence.*;

@Entity
@Table(name = "Genre")
public class GenreDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private int genreId;

    @Column(name = "name", nullable = false, unique = true, length = 100)
    private String name;

    @Column(name = "is_featured")
    private boolean isFeatured = false;

    @Column(name = "image", length = 255)
    private String image;

    public GenreDTO() {}

    public GenreDTO(int genreId, String name, boolean isFeatured, String image) {
        this.genreId = genreId;
        this.name = name;
        this.isFeatured = isFeatured;
        this.image = image;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
