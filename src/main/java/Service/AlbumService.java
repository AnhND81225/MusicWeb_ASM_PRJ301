package Service;

import Model.DAO.AlbumDao;
import Model.DTO.AlbumDTO;
import Model.DTO.SongDTO;

import java.util.List;

public class AlbumService {

    private final AlbumDao albumDao = new AlbumDao();

    // Tạo album mới
    public AlbumDTO createAlbum(AlbumDTO album) {
        return albumDao.createAlbum(album);
    }

    // Cập nhật album
    public void updateAlbum(AlbumDTO album) {
        albumDao.updateAlbum(album);
    }

    // Lấy album theo ID
    public AlbumDTO getAlbumById(int albumId) {
        return albumDao.getByID(albumId);
    }

    // Xóa mềm album
    public boolean hideAlbum(int albumId) {
        return albumDao.hideAlbum(albumId);
    }

    // Khôi phục album đã bị ẩn
    public boolean restoreAlbum(int albumId) {
        return albumDao.restoreAlbum(albumId);
    }

    // Lấy tất cả album chưa bị ẩn
    public List<AlbumDTO> getAllAlbums() {
        return albumDao.viewAllAlbums();
    }

    // Lấy danh sách album đã bị ẩn
    public List<AlbumDTO> getHiddenAlbums() {
        return albumDao.viewHiddenAlbums();
    }

    // Tìm album theo tên
    public List<AlbumDTO> searchAlbums(String keyword) {
        return albumDao.searchAlbumsByName(keyword);
    }

    // Lấy album theo nghệ sĩ
    public List<AlbumDTO> getAlbumsByArtist(int artistId) {
        return albumDao.getAlbumsByArtist(artistId);
    }

    // Lấy album theo thể loại
    public List<AlbumDTO> getAlbumsByGenre(int genreId) {
        return albumDao.getAlbumsByGenre(genreId);
    }

    // Thêm bài hát vào album
    public SongDTO addSongToAlbum(SongDTO song, int albumId) {
        return albumDao.addSongToAlbum(song, albumId);
    }

    public List<AlbumDTO> getFeaturedAlbums() {
        return albumDao.getFeaturedAlbums();
    }

    public List<AlbumDTO> getNewAlbums() {
        return albumDao.getNewAlbums();
    }

    public List<AlbumDTO> getAlbumsSortedByReleaseDate() {
        return albumDao.getAlbumsSortedByReleaseDate();
    }

}
