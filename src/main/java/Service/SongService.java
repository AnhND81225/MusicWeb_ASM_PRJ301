package Service;

import Model.DAO.SongDao;
import Model.DTO.SongDTO;

import java.util.List;

public class SongService {

    private final SongDao songDao = new SongDao();

    // Thêm bài hát mới
    public boolean addSong(SongDTO song) {
        return songDao.insert(song);
    }

    // Cập nhật bài hát
    public boolean updateSong(SongDTO song) {
        return songDao.update(song);
    }

    // Xóa mềm bài hát
    public boolean hideSong(int songId) {
        return songDao.hide(songId);
    }

    // Khôi phục bài hát đã bị ẩn
    public boolean restoreSong(int songId) {
        return songDao.restore(songId);
    }

    // Lấy tất cả bài hát chưa bị ẩn
    public List<SongDTO> getAllSongs() {
        return songDao.getAllSongs();
    }

    // Lấy bài hát theo ID
    public SongDTO getSongById(int songId) {
        return songDao.getById(songId);
    }

    // Tìm kiếm bài hát theo từ khóa
    public List<SongDTO> searchSongs(String keyword) {
        return songDao.searchByKeyword(keyword);
    }

    // Lấy bài hát theo album
    public List<SongDTO> getSongsByAlbum(int albumId) {
        return songDao.getSongsByAlbum(albumId);
    }

    // Lấy bài hát theo nghệ sĩ
    public List<SongDTO> getSongsByArtist(int artistId) {
        return songDao.getSongsByArtist(artistId);
    }

    // Lấy bài hát theo thể loại
    public List<SongDTO> getSongsByGenre(int genreId) {
        return songDao.getSongsByGenre(genreId);
    }

    // Lấy danh sách bài hát đã bị ẩn
    public List<SongDTO> getHiddenSongs() {
        return songDao.getHiddenSongs();
    }
}
