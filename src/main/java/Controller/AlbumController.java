package Controller;

import Model.DTO.AlbumDTO;
import Service.AlbumService;
import Service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "AlbumController", urlPatterns = {"/AlbumController"})
public class AlbumController extends HttpServlet {

    private final AlbumService albumService = new AlbumService();
    private final ValidationService validator = new ValidationService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = Optional.ofNullable(request.getParameter("txtAction")).orElse("viewAlbum");

        switch (action) {
            case "addAlbum":
                handleAddAlbum(request, response);
                break;
            case "updateAlbum":
                handleUpdateAlbum(request, response);
                break;
            case "hideAlbum":
                handleHideAlbum(request, response);
                break;
            case "restoreAlbum":
                handleRestoreAlbum(request, response);
                break;
            case "searchAlbum":
                handleSearchAlbum(request, response);
                break;
            case "viewHidden":
                handleViewHiddenAlbums(request, response);
                break;
            case "featured":
                handleFeaturedAlbums(request, response);
                break;
            case "sorted":
                handleSortedAlbums(request, response);
                break;
            case "viewAlbum":
            default:
                handleViewAlbums(request, response);
                break;
        }
    }

    private void handleViewAlbums(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AlbumDTO> albums = albumService.getAllAlbums();
        request.setAttribute("listOfAlbums", albums);
        request.getRequestDispatcher("listOfAlbums.jsp").forward(request, response);
    }

    private void handleViewHiddenAlbums(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AlbumDTO> albums = albumService.getHiddenAlbums();
        request.setAttribute("listOfAlbums", albums);
        request.getRequestDispatcher("listOfHiddenAlbums.jsp").forward(request, response);
    }

    private void handleAddAlbum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AlbumDTO album = extractAlbumFromRequest(request, false);

        if (!validator.isValid(album)) {
            request.setAttribute("error", "Thông tin album không hợp lệ.");
            request.setAttribute("a", album);
            request.getRequestDispatcher("albumForm.jsp").forward(request, response);
            return;
        }

        albumService.createAlbum(album);
        response.sendRedirect("AlbumController?txtAction=viewAlbum");
    }

    private void handleUpdateAlbum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AlbumDTO album = extractAlbumFromRequest(request, true);

        if (!validator.isValid(album)) {
            request.setAttribute("error", "Thông tin album không hợp lệ.");
            request.setAttribute("a", album);
            request.getRequestDispatcher("albumForm.jsp").forward(request, response);
            return;
        }

        albumService.updateAlbum(album);
        response.sendRedirect("AlbumController?txtAction=viewAlbum");
    }

    private void handleHideAlbum(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("albumID"));
        albumService.hideAlbum(id);
        response.sendRedirect("AlbumController?txtAction=viewAlbum");
    }

    private void handleRestoreAlbum(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("albumID"));
        albumService.restoreAlbum(id);
        response.sendRedirect("AlbumController?txtAction=viewHidden");
    }

    private void handleSearchAlbum(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = Optional.ofNullable(request.getParameter("keyword")).orElse("").trim();
        List<AlbumDTO> albums = albumService.searchAlbums(keyword);
        request.setAttribute("searchKeyword", keyword);
        request.setAttribute("listOfAlbums", albums);
        request.getRequestDispatcher("searchAlbumResults.jsp").forward(request, response);
    }

    private void handleFeaturedAlbums(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AlbumDTO> albums = albumService.getFeaturedAlbums();
        request.setAttribute("listOfAlbums", albums);
        request.getRequestDispatcher("listOfAlbums.jsp").forward(request, response);
    }

    private void handleSortedAlbums(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<AlbumDTO> albums = albumService.getAlbumsSortedByReleaseDate();
        request.setAttribute("listOfAlbums", albums);
        request.getRequestDispatcher("listOfAlbums.jsp").forward(request, response);
    }

    private AlbumDTO extractAlbumFromRequest(HttpServletRequest request, boolean isUpdate) {
        AlbumDTO album = new AlbumDTO();

        if (isUpdate) {
            int id = Integer.parseInt(request.getParameter("albumID"));
            album.setAlbumId(id);
        }

        album.setName(request.getParameter("name"));

        String dateStr = request.getParameter("releaseDate");
        try {
            LocalDateTime releaseDate = LocalDate.parse(dateStr).atStartOfDay();
            album.setReleaseDate(releaseDate);
        } catch (Exception e) {
            album.setReleaseDate(null); // để validator bắt lỗi
        }

        return album;
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
