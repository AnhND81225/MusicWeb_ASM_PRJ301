/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import Model.DAO.UserDAO;
import Model.DTO.UserDTO;
import Service.UserService;
import Util.OTPUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "UserController", urlPatterns = {"/UserController"})
public class UserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            UserService userService = new UserService();
            HttpSession session = request.getSession();
            String url = "login.jsp";

            boolean statusLogin = userService.login(username, password);
            if (statusLogin) {
                url = "HomePage.jsp";
                UserDTO user = userService.getUserByUserName(username);
                session.setAttribute("user", user);
            } else {
                request.setAttribute("msg", "Invalid Username or password!");
                request.setAttribute("username", username);
            }

            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    protected void processVerifyOTP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            System.out.println("VerifyOTP");
            String otpSendStr = request.getParameter("otpSend");
            HttpSession session = request.getSession();

            LocalDateTime otpSend = LocalDateTime.parse(otpSendStr.replace(" ", "T")); // chuyển ' ' → 'T' nếu có
            String otpOriginal = (String) session.getAttribute("otp");
            String otpInput = request.getParameter("txtOTP"); // OTP người dùng nhập

            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            UserService userService = new UserService();
            String url = "";
            String statusConfirm = OTPUtil.verifyOTP(otpOriginal, otpInput, otpSend, LocalDateTime.now());
            if (statusConfirm.equalsIgnoreCase("Ok")) {
                url = "HomePage.jsp";
                String statusRegister = userService.register(email, username, password);
                if (!statusRegister.equalsIgnoreCase("Ok")) {
                    url = "register.jsp";
                    request.setAttribute("error", statusRegister);
                }
            }

            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    protected void processRegister(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            String email = request.getParameter("txtEmail");
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            HttpSession session = request.getSession();
            String otp = String.format("%06d", new Random().nextInt(1000000)); // đảm bảo 6 chữ số
            session.setAttribute("otp", otp); // lưu vào session thay vì gửi lại qua JSP            String url = "";
            LocalDateTime otpSend = LocalDateTime.now();
            UserDAO userDAO = new UserDAO();
//            boolean checkUser = userDAO.isUserExist(username);
//            System.out.println(checkUser);
//            if (checkUser) {
//                System.out.println("exist");
//                url = "register.jsp";
//                request.setAttribute("error", "Username is already exist!");
//            } else {
            System.out.println("no exist");

            String url = "otpAuthenticator.jsp";
            OTPUtil.sendEmail(email, "OTP Authentication", "Your OTP is: " + otp);

            request.setAttribute("otp", otp);
            request.setAttribute("otpSend", otpSend);
            request.setAttribute("email", email);
            request.setAttribute("username", username);
            request.setAttribute("password", password);

            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    protected void processLogout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            session.invalidate();
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String action = request.getParameter("txtAction");

            if (action.equalsIgnoreCase("login")) {
                processLogin(request, response);
            } else if (action.equalsIgnoreCase("register")) {
                processRegister(request, response);
            } else if (action.equalsIgnoreCase("verifyOTP")) {
                processVerifyOTP(request, response);
            } else if (action.equalsIgnoreCase("logout")) {
                processLogout(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
