package Service;

import Model.DAO.ArtistDao;
import Model.DTO.ArtistDTO;

import java.util.List;

public class ArtistService {

    private final ArtistDao artistDao = new ArtistDao();

    // Thêm nghệ sĩ mới
    public boolean addArtist(ArtistDTO artist) {
        return artistDao.insert(artist);
    }

    // Cập nhật thông tin nghệ sĩ
    public boolean updateArtist(ArtistDTO artist) {
        return artistDao.update(artist);
    }

    // Lấy tất cả nghệ sĩ chưa bị ẩn
    public List<ArtistDTO> getAllArtists() {
        return artistDao.getAllArtists();
    }

    // Lấy nghệ sĩ theo ID
    public ArtistDTO getArtistById(int artistId) {
        return artistDao.getById(artistId);
    }

    // Ẩn nghệ sĩ (xóa mềm)
    public boolean hideArtist(int artistId) {
        return artistDao.hideArtist(artistId);
    }

    // Khôi phục nghệ sĩ đã bị ẩn
    public boolean restoreArtist(int artistId) {
        return artistDao.restoreArtist(artistId);
    }

    // Tìm kiếm nghệ sĩ theo tên
    public List<ArtistDTO> searchArtists(String keyword) {
        return artistDao.searchByName(keyword);
    }

    // Lấy danh sách nghệ sĩ đã bị ẩn
    public List<ArtistDTO> getHiddenArtists() {
        return artistDao.getHiddenArtists();
    }
}
