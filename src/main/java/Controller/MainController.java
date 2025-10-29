package Controller;

import java.io.IOException;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
  
        String txtAction = request.getParameter("txtAction");
        String url = "HomePage.jsp";

        // Các hành động liên quan đến người dùng
        String[] userActions = {"login", "logout", "register", "updateProfile", "viewProfile", "verifyOTP"};

        // Các hành động liên quan đến bài hát
        String[] songActions = {"addSong", "deleteSong", "updateSong", "viewSong", "searchSong"};

        if (txtAction != null) {
            if (Arrays.asList(userActions).contains(txtAction)) {
                url = "UserController";
            } else if (Arrays.asList(songActions).contains(txtAction)) {
                url = "SongController";
            }
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
