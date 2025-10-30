package Controller;

import Service.SongService;
import Service.AlbumService;
import Service.ArtistService;
import Model.DTO.AlbumDTO;
import Model.DTO.ArtistDTO;
import Model.DTO.SongDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "SearchController", urlPatterns = {"/SearchController"})
public class SearchController extends HttpServlet {

    private final SongService songService = new SongService();
    private final AlbumService albumService = new AlbumService();
    private final ArtistService artistService = new ArtistService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String keyword = Optional.ofNullable(req.getParameter("keyword")).orElse("").trim();

        // Tìm kiếm cả 3 loại
        List<SongDTO> songs = songService.searchSongs(keyword);
        List<AlbumDTO> albums = albumService.searchAlbums(keyword);
        List<ArtistDTO> artists = artistService.searchArtists(keyword);

        req.setAttribute("searchKeyword", keyword);

        boolean hasSongs = songs != null && !songs.isEmpty();
        boolean hasAlbums = albums != null && !albums.isEmpty();
        boolean hasArtists = artists != null && !artists.isEmpty();

        // Điều hướng thông minh
        if (hasSongs && !hasAlbums && !hasArtists) {
            req.setAttribute("listOfSongs", songs);
            req.getRequestDispatcher("songSearchResults.jsp").forward(req, res);
        } else if (!hasSongs && hasAlbums && !hasArtists) {
            req.setAttribute("listOfAlbums", albums);
            req.getRequestDispatcher("albumSearchResults.jsp").forward(req, res);
        } else if (!hasSongs && !hasAlbums && hasArtists) {
            req.setAttribute("listOfArtists", artists);
            req.getRequestDispatcher("artistSearchResults.jsp").forward(req, res);
        } else {
            // Có nhiều loại kết quả → chuyển đến trang tổng hợp
            req.setAttribute("listOfSongs", songs);
            req.setAttribute("listOfAlbums", albums);
            req.setAttribute("listOfArtists", artists);
            req.getRequestDispatcher("searchAllResults.jsp").forward(req, res);
        }
    }
}
