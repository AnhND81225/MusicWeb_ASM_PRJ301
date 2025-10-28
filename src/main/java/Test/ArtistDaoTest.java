package Test;

import Model.DAO.ArtistDao;
import Model.DTO.ArtistDTO;

import java.util.List;

public class ArtistDaoTest {
    public static void main(String[] args) {
        ArtistDao artistDao = new ArtistDao();

        // ✅ Display active artists (not hidden)
        System.out.println("\n🎤 List of active artists:");
        List<ArtistDTO> activeArtists = artistDao.getAllArtists();
        for (ArtistDTO artist : activeArtists) {
            System.out.println(" - " + artist.getName() + " (ID: " + artist.getArtistId() + ")");
        }

        // ✅ Search artists by name
        System.out.println("\n🔍 Search for artists with keyword 'tâm':");
        List<ArtistDTO> searchResults = artistDao.searchByName("tâm");
        for (ArtistDTO artist : searchResults) {
            System.out.println(" - " + artist.getName());
        }

        // ✅ Hide artist by ID (example: ID = 1)
        System.out.println("\n🚫 Hiding artist with ID = 1");
        artistDao.hideArtist(1);

        // ✅ Restore artist by ID (example: ID = 1)
        System.out.println("\n✅ Restoring artist with ID = 1");
        artistDao.restoreArtist(1);

        // ✅ Display hidden artists
        System.out.println("\n🙈 List of hidden artists:");
        List<ArtistDTO> hiddenArtists = artistDao.getHiddenArtists();
        for (ArtistDTO artist : hiddenArtists) {
            System.out.println(" - " + artist.getName());
        }
    }
}
