package nl.stagesync.stagesync.controller;

import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.payload.request.CreateArtistRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistController {

    private ArtistService artistService;

    @Autowired
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/artists")
    public ResponseEntity<Object> getAllArtists(Principal principal) {
        return ResponseEntity.ok(artistService.getAllArtists(principal));
    }

    @GetMapping("/artists/names/{name}")
    public ResponseEntity<Object> findArtistsByName(@PathVariable("name") String name) {
        List<Artist> artists = artistService.getArtistsNameStartsWith(name);
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Object> getArtistById(@PathVariable("id") long id) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @PostMapping("/artists")
    public ResponseEntity<MessageResponse> createArtist(@RequestBody CreateArtistRequest createArtistRequest, Principal principal) {
       return artistService.createArtist(createArtistRequest, principal);
    }

    @DeleteMapping("/artists/{id}")
    public ResponseEntity<Object> deleteArtist(@PathVariable("id") long id) {
        artistService.deleteById(id);
        return new ResponseEntity<>("Artist deleted", HttpStatus.OK);
    }

}
