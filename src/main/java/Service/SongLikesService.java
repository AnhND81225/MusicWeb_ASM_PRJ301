/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DAO.SongLikesDAO;
import Model.DTO.SongDTO;
import Model.DTO.SongLikesDTO;
import Model.DTO.UserDTO;
import java.util.List;

public class SongLikesService {

    private SongLikesDAO dao;

    public SongLikesService(SongLikesDAO dao) {
        this.dao = dao;
    }

    // Thêm like
  public boolean addLike(UserDTO user, SongDTO song) {
    SongLikesDTO existing = dao.selectByUserAndSong(user, song);
    if (existing == null) {
        return dao.insert(new SongLikesDTO(user, song)) > 0;
    }
    return false;
}

    // Bỏ like (xóa mềm)
    public boolean removeLike(SongLikesDTO like) {
        return dao.deleteSoft(like) > 0;
    }

    // Lấy tất cả like (có thể bao gồm ẩn)
    public List<SongLikesDTO> getAllLikes() {
        return dao.selectAll();
    }

    // Lấy tất cả like hiển thị (isHidden = false)
    public List<SongLikesDTO> getAllVisibleLikes() {
        return dao.selectAllVisible();
    }

    // Lấy like theo ID
    public SongLikesDTO getLikeById(Integer id) {
        return dao.selectById(id);
    }

    
}