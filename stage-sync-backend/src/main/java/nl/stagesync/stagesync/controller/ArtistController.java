package nl.stagesync.stagesync.controller;

import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.payload.request.CreateArtistRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.service.ArtistService;
import nl.stagesync.stagesync.service.RiderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistController {

    private ArtistService artistService;
    private RiderService riderService;

    @Autowired
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Autowired
    public void setRiderService(RiderService riderService) {
        this.riderService = riderService;
    }


    @GetMapping("/artists")
    public ResponseEntity<Object> getAllArtists(Principal principal) {
        return ResponseEntity.ok(artistService.getAllArtists(principal));
    }

    @GetMapping("/artists/artist/{name}")
    public ResponseEntity<Object> findArtistsByName(@PathVariable("name") String name) {
        List<Artist> artists = artistService.getArtistsNameStartsWith(name);
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/artists/riders")
    public ResponseEntity<Object> getAllRiders() {
        return ResponseEntity.ok(riderService.getAllRiders());
    }

    @PostMapping(value = "/artists/riders", consumes = "multipart/form-data")
    public void addRiderToArtist(@RequestPart("rider") MultipartFile[] multipartFiles) throws IOException, NoSuchAlgorithmException {
        riderService.addRiders(multipartFiles);
    }

    @GetMapping("/artists/id/{id}")
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
