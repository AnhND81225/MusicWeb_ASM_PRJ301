/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Test;

import Model.DAO.UserDAO;
import Model.DTO.UserDTO;
import Service.UserService;
import Util.HibernateUtil;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author phant
 */
public class TestDAO {
    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAO();
            UserService userService = new UserService();
//            UserDTO userDTO = new UserDTO("admin", userService.hashPassword("duck8235"), "admin8235@gmail.com", "Admin");
            UserDTO userDTO = userDAO.getUserByUsername("admin");
            userDTO.setHidden(false);
            userDAO.updateUser(userDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
