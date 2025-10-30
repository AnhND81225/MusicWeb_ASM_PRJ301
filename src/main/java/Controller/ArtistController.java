package Controller;

import Model.DTO.ArtistDTO;
import Service.ArtistService;
import Service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ArtistController", urlPatterns = {"/ArtistController"})
public class ArtistController extends HttpServlet {

    private final ArtistService artistService = new ArtistService();
    private final ValidationService validator = new ValidationService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = Optional.ofNullable(request.getParameter("txtAction")).orElse("viewArtist");

        switch (action) {
            case "addArtist":
                handleAddArtist(request, response);
                break;
            case "updateArtist":
                handleUpdateArtist(request, response);
                break;
            case "hideArtist":
                handleHideArtist(request, response);
                break;
            case "restoreArtist":
                handleRestoreArtist(request, response);
                break;
            case "search":
                handleSearchArtist(request, response);
                break;
            case "viewArtist":
            default:
                handleViewArtists(request, response);
                break;
        }
    }

    private void handleViewArtists(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ArtistDTO> artists = artistService.getAllArtists();
        request.setAttribute("listOfArtists", artists);
        request.getRequestDispatcher("listOfArtists.jsp").forward(request, response);
    }

    private void handleAddArtist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String bio = request.getParameter("bio");

        ArtistDTO artist = new ArtistDTO();
        artist.setName(name);
        artist.setBio(bio);

        if (!validator.isValid(artist)) {
            request.setAttribute("error", "Thông tin nghệ sĩ không hợp lệ.");
            request.setAttribute("a", artist);
            request.getRequestDispatcher("artistForm.jsp").forward(request, response);
            return;
        }

        artistService.addArtist(artist);
        response.sendRedirect("ArtistController?txtAction=viewArtist");
    }

    private void handleUpdateArtist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("artistID"));
        String name = request.getParameter("name");
        String bio = request.getParameter("bio");

        ArtistDTO artist = new ArtistDTO();
        artist.setArtistId(id);
        artist.setName(name);
        artist.setBio(bio);

        if (!validator.isValid(artist)) {
            request.setAttribute("error", "Thông tin nghệ sĩ không hợp lệ.");
            request.setAttribute("a", artist);
            request.getRequestDispatcher("artistForm.jsp").forward(request, response);
            return;
        }

        artistService.updateArtist(artist);
        response.sendRedirect("ArtistController?txtAction=viewArtist");
    }

    private void handleHideArtist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("artistID"));
        artistService.hideArtist(id);
        response.sendRedirect("ArtistController?txtAction=viewArtist");
    }

    private void handleRestoreArtist(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("artistID"));
        artistService.restoreArtist(id);
        response.sendRedirect("ArtistController?txtAction=viewArtist");
    }

    private void handleSearchArtist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = Optional.ofNullable(request.getParameter("keyword")).orElse("").trim();
        List<ArtistDTO> artists = artistService.searchArtists(keyword);
        request.setAttribute("searchKeyword", keyword);
        request.setAttribute("listOfArtists", artists);
        request.getRequestDispatcher("searchArtistResults.jsp").forward(request, response);
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
