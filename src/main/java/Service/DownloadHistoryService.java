package Service;

import Model.DAO.DownloadHistoryDAO;
import Model.DTO.DownloadHistoryDTO;
import Model.DTO.SongDTO;
import Model.DTO.UserDTO;
import java.util.List;

public class DownloadHistoryService {

    private DownloadHistoryDAO dao;

    public DownloadHistoryService(DownloadHistoryDAO dao) {
        this.dao = dao;
    }

   // Thêm download mới
    public boolean addDownload(DownloadHistoryDTO download) {
        return dao.insert(download) > 0;
    }

    // Xóa mềm (ẩn download)
    public boolean deleteDownloadSoft(Integer id) {
        return dao.deleteSoft(id) > 0;
    }

    //  Lấy tất cả download chưa bị ẩn
    public List<DownloadHistoryDTO> getAllDownloads() {
        return dao.selectAll();
    }

    //  Lấy download của 1 user
    public List<DownloadHistoryDTO> getDownloadsByUser(UserDTO user) {
        return dao.selectByUser(user);
    }

    // Lấy download theo bài hát
    public List<DownloadHistoryDTO> getDownloadsBySong(SongDTO song) {
        return dao.selectBySong(song);
    }

    //  Lấy download theo ID
    public DownloadHistoryDTO getDownloadById(Integer id) {
        return dao.selectById(id);
    }
}