package Controller;

import Model.DTO.SongDTO;
import Service.SongService;
import Service.ValidationService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "SongController", urlPatterns = {"/SongController"})
public class SongController extends HttpServlet {

    private final SongService songService = new SongService();
    private final ValidationService validator = new ValidationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        processRequest(req, res);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String action = Optional.ofNullable(req.getParameter("txtAction")).orElse("view");
        switch (action) {
            case "add":
                handleSaveSong(req, res, false);
                break;
            case "update":
                handleSaveSong(req, res, true);
                break;
            case "callUpdate":
                handleCallUpdate(req, res);
                break;
            case "hide":
                handleHideSong(req, res);
                break;
            case "restore":
                handleRestoreSong(req, res);
                break;
            case "viewHidden":
                handleViewHiddenSongs(req, res);
                break;
            case "top":
                handleViewTopSongs(req, res);
                break;
            case "play":
                handlePlaySong(req, res);
                break;
            case "view":
            default:
                handleViewSongs(req, res);
                break;
        }

    }

    private void handleViewSongs(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<SongDTO> songs = songService.getAllSongs();
        req.setAttribute("listOfSongs", songs);
        req.getRequestDispatcher("listOfSongs.jsp").forward(req, res);

    }

    private void handleViewHiddenSongs(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<SongDTO> songs = songService.getHiddenSongs();
        req.setAttribute("listOfSongs", songs);
        req.getRequestDispatcher("listOfHiddenSongs.jsp").forward(req, res);

    }

    private void handleViewTopSongs(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<SongDTO> songs = songService.getTopSongs(10);
        req.setAttribute("listOfSongs", songs);
        req.getRequestDispatcher("topSongs.jsp").forward(req, res);

    }

    private void handleCallUpdate(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("songID"));
            SongDTO song = songService.getSongById(id);
            req.setAttribute("s", song);
            req.setAttribute("update", true);
            req.getRequestDispatcher("songForm.jsp").forward(req, res);

        } catch (Exception e) {
            res.sendRedirect("SongController?txtAction=view");
        }
    }

    private void handleSaveSong(HttpServletRequest req, HttpServletResponse res, boolean update)
            throws ServletException, IOException {
        SongDTO song = extractSong(req);

        if (!validator.isValid(song)) {
            req.setAttribute("error", "Thông tin bài hát không hợp lệ.");
            req.setAttribute("s", song);
            req.getRequestDispatcher("songForm.jsp").forward(req, res);

            return;
        }

        boolean success = update ? songService.updateSong(song) : songService.addSong(song);
        if (!success) {
            req.setAttribute("error", "Không thể lưu bài hát.");
            req.setAttribute("s", song);
            req.getRequestDispatcher("songForm.jsp").forward(req, res);

            return;
        }

        res.sendRedirect("SongController?txtAction=view");
    }

    private SongDTO extractSong(HttpServletRequest req) {
        String title = req.getParameter("title");
        String filePath = req.getParameter("filePath");
        int duration = 0;
        try {
            duration = Integer.parseInt(req.getParameter("duration"));
        } catch (NumberFormatException e) {
            duration = -1;
        }
        SongDTO song = new SongDTO();
        song.setTitle(title);
        song.setFilePath(filePath);
        song.setDuration(duration);
        return song;
    }

    private void handleHideSong(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        int id = Integer.parseInt(req.getParameter("songID"));
        songService.hideSong(id);
        res.sendRedirect("SongController?txtAction=view");
    }

    private void handleRestoreSong(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        int id = Integer.parseInt(req.getParameter("songID"));
        songService.restoreSong(id);
        res.sendRedirect("SongController?txtAction=viewHidden");
    }

    private void handlePlaySong(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        int id = Integer.parseInt(req.getParameter("songID"));
        songService.increasePlayCount(id);
        res.sendRedirect("SongController?txtAction=view");
    }

    @Override
    public String getServletInfo() {
        return "SongController handles all song-related actions";
    }
}
