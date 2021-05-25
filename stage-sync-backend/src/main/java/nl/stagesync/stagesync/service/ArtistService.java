package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.payload.request.CreateArtistRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

public interface ArtistService {

    Set<Artist> getAllArtists(Principal principal);
    Artist getArtistById(long id);
    List<Artist> getArtistsNameStartsWith(String name);
    ResponseEntity<MessageResponse> createArtist(CreateArtistRequest createArtistRequest, Principal principal);
    void deleteById(long id);
}
