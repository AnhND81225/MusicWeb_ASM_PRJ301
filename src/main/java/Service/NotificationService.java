/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.DAO.NotificationDAO;
import Model.DTO.NotificationDTO;
import java.util.List;

public class NotificationService {

    private NotificationDAO notificationDAO;

    public NotificationService(NotificationDAO notificationDAO) {
        this.notificationDAO = notificationDAO;
    }

    // Thêm thông báo
    public boolean addNotification(NotificationDTO notification) {
        return notificationDAO.insert(notification) > 0;
    }

    // Xóa mềm thông báo
    public boolean deleteNotification(NotificationDTO notification) {
        return notificationDAO.deleteSoft(notification) > 0;
    }

    // Lấy tất cả thông báo
    public List<NotificationDTO> getAllNotifications() {
        return notificationDAO.selectAll();
    }

    // Lấy tất cả thông báo hiển thị (isHidden = false)
    public List<NotificationDTO> getAllVisibleNotifications() {
        return notificationDAO.selectAllVisible();
    }

    // Lấy thông báo theo ID
    public NotificationDTO getNotificationById(Integer id) {
        return notificationDAO.selectById(id);
    }
    // Lấy thông báo theo user

    public List<NotificationDTO> getNotificationsByUserId(Integer userId) {
        return notificationDAO.selectByUserId(userId);
    }

// Lấy thông báo theo bài hát
    public List<NotificationDTO> getNotificationsBySongId(Integer songId) {
        return notificationDAO.selectBySongId(songId);
    }
    // đánh dấu đã đọc
    public boolean markNotificationAsRead(Integer notificationId) {
    return notificationDAO.markAsRead(notificationId) > 0;
}
    //lấy thông báo chưa đọc theo user
    public List<NotificationDTO> getUnreadNotificationsByUserId(Integer userId) {
    return notificationDAO.selectUnreadByUserId(userId);
}
}