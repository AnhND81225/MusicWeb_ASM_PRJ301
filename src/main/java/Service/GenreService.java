package Service;

import Model.DAO.GenreDao;
import Model.DTO.GenreDTO;

import java.util.List;

public class GenreService {

    private final GenreDao genreDao = new GenreDao();

    // Thêm thể loại mới
    public boolean addGenre(GenreDTO genre) {
        return genreDao.insert(genre);
    }

    // Cập nhật thể loại
    public boolean updateGenre(GenreDTO genre) {
        return genreDao.update(genre);
    }

    // Lấy tất cả thể loại chưa bị ẩn
    public List<GenreDTO> getAllGenres() {
        return genreDao.getAllGenre();
    }

    // Tìm thể loại theo tên
    public List<GenreDTO> searchGenres(String keyword) {
        return genreDao.getByName(keyword);
    }

    // Lấy thể loại theo ID
    public GenreDTO getGenreById(int genreId) {
        return genreDao.getById(genreId);
    }

    public List<GenreDTO> getFeaturedGenres() {
        return genreDao.getFeaturedGenres();
    }

}
