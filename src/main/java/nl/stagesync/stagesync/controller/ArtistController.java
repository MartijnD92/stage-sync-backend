package nl.stagesync.stagesync.controller;

import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.payload.request.CreateArtistRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/artists")
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllArtists(Principal principal) {
        return ResponseEntity.ok(artistService.getAllArtists(principal));
    }

    @GetMapping("/artist/{name}")
    public ResponseEntity<Object> findArtistsByName(@PathVariable("name") String name) {
        List<Artist> artists = artistService.getArtistByNameEager(name);
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/artist/id/{id}")
    public ResponseEntity<Object> getArtistById(@PathVariable("id") long id) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createArtist(@RequestBody CreateArtistRequest createArtistRequest, Principal principal) throws IOException {
        artistService.createArtist(createArtistRequest, principal);
        return ResponseEntity.ok(new MessageResponse("Artist created successfully!"));
    }


    @DeleteMapping("/artist/id/{id}")
    public ResponseEntity<Object> deleteArtistById(@PathVariable("id") Long id) {
        artistService.deleteById(id);
        return new ResponseEntity<>("Artist deleted", HttpStatus.OK);
    }

}
