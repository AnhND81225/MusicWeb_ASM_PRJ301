    package Controller;

import Model.DTO.GenreDTO;
import Service.GenreService;
import Service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "GenreController", urlPatterns = {"/GenreController"})
public class GenreController extends HttpServlet {

    private final GenreService genreService = new GenreService();
    private final ValidationService validator = new ValidationService();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = Optional.ofNullable(request.getParameter("txtAction")).orElse("viewGenre");

        switch (action) {
            case "addGenre":
                handleAddGenre(request, response);
                break;
            case "updateGenre":
                handleUpdateGenre(request, response);
                break;
            case "searchGenre":
                handleSearchGenre(request, response);
                break;
            case "featured":
                handleFeaturedGenres(request, response);
                break;
            case "viewGenre":
            default:
                handleViewGenres(request, response);
                break;
        }
    }

    private void handleViewGenres(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<GenreDTO> genres = genreService.getAllGenres();
        request.setAttribute("listOfGenres", genres);
        request.getRequestDispatcher("listOfGenres.jsp").forward(request, response);
    }

    private void handleAddGenre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        GenreDTO genre = new GenreDTO();
        genre.setName(name);

        if (!validator.isValid(genre)) {
            request.setAttribute("error", "Tên thể loại không hợp lệ.");
            request.setAttribute("g", genre);
            request.getRequestDispatcher("genreForm.jsp").forward(request, response);
            return;
        }

        genreService.addGenre(genre);
        response.sendRedirect("GenreController?txtAction=viewGenre");
    }

    private void handleUpdateGenre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("genreID"));
        String name = request.getParameter("name");
        GenreDTO genre = new GenreDTO();
        genre.setGenreId(id);
        genre.setName(name);

        if (!validator.isValid(genre)) {
            request.setAttribute("error", "Thông tin thể loại không hợp lệ.");
            request.setAttribute("g", genre);
            request.getRequestDispatcher("genreForm.jsp").forward(request, response);
            return;
        }

        genreService.updateGenre(genre);
        response.sendRedirect("GenreController?txtAction=viewGenre");
    }

    private void handleSearchGenre(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = Optional.ofNullable(request.getParameter("keyword")).orElse("").trim();
        List<GenreDTO> genres = genreService.searchGenres(keyword);
        request.setAttribute("searchKeyword", keyword);
        request.setAttribute("listOfGenres", genres);
        request.getRequestDispatcher("searchGenreResults.jsp").forward(request, response);
    }

    private void handleFeaturedGenres(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<GenreDTO> genres = genreService.getFeaturedGenres();
        request.setAttribute("listOfGenres", genres);
        request.getRequestDispatcher("listOfGenres.jsp").forward(request, response);
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
