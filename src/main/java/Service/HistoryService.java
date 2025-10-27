/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DAO.HistoryDAO;
import Model.DTO.HistoryDTO;
import Model.DTO.SongDTO;
import Model.DTO.UserDTO;
import java.util.List;

public class HistoryService {

    private HistoryDAO historyDAO;

    public HistoryService(HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    // 🟢 Thêm mới lịch sử nghe nhạc
    public boolean addHistory(HistoryDTO history) {
        return historyDAO.insert(history) > 0;
    }

    // 🟡 Xóa mềm lịch sử theo ID
    public boolean deleteHistorySoft(Integer id) {
        return historyDAO.deleteSoft(id) > 0;
    }

    // 🟢 Lấy tất cả lịch sử chưa bị ẩn
    public List<HistoryDTO> getAllHistory() {
        return historyDAO.selectAll();
    }

    // 🟢 Lấy lịch sử theo ID
    public HistoryDTO getHistoryById(Integer id) {
        return historyDAO.selectById(id);
    }

    // 🟢 Lấy lịch sử nghe nhạc theo người dùng
    public List<HistoryDTO> getHistoryByUser(UserDTO user) {
        return historyDAO.selectByUser(user);
    }

    // 🟢 Lấy lịch sử nghe nhạc theo bài hát
    public List<HistoryDTO> getHistoryBySong(SongDTO song) {
        return historyDAO.selectBySong(song);
    }
}
