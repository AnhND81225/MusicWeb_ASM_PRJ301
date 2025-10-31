package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String HOME_PAGE = "HomePage.jsp";

    private static final String[] USER_ACTIONS = {"login", "logout", "register", "updateProfile", "viewProfile"};
    private static final String[] SONG_ACTIONS = {"add", "update", "callUpdate", "hide", "restore", "viewHidden", "top", "play", "view"};
    private static final String[] ARTIST_ACTIONS = {"addArtist", "updateArtist", "viewArtist"};
    private static final String[] ALBUM_ACTIONS = {"addAlbum", "viewAlbum"};
    private static final String[] GENRE_ACTIONS = {"addGenre", "viewGenre"};
   

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("txtAction");
        String url = HOME_PAGE;

        if (action != null) {
            if (Arrays.asList(USER_ACTIONS).contains(action)) {
                url = "UserController";
            } else if (Arrays.asList(SONG_ACTIONS).contains(action)) {
                url = "SongController";
            } else if (Arrays.asList(ARTIST_ACTIONS).contains(action)) {
                url = "ArtistController";
            } else if (Arrays.asList(ALBUM_ACTIONS).contains(action)) {
                url = "AlbumController";
            } else if (Arrays.asList(GENRE_ACTIONS).contains(action)) {
                url = "GenreController";
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