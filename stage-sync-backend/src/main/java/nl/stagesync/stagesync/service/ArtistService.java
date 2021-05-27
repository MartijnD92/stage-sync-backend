package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.payload.request.CreateArtistRequest;
import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface ArtistService {

    Set<Artist> getAllArtists(Principal principal);
    Artist getArtistById(long id);
    Artist getArtistByName(String name);
    List<Artist> getArtistsNameStartsWith(String name);
    void createArtist(CreateArtistRequest createArtistRequest, Principal principal);
    void deleteById(long id);
}
