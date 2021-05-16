package nl.stagesync.stagesync.controller;

import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists")
    public ResponseEntity<Object> getArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @GetMapping("/artists/names/{name}")
    public ResponseEntity<Object> getArtists(@PathVariable("name") String name) {
        List<Artist> artists = artistService.getArtistsNameStartsWith(name);
        return ResponseEntity.ok(artists);
    }

    @GetMapping("/artists/{id}")
    public ResponseEntity<Object> getArtistById(@PathVariable("id") long id) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @PostMapping("/artists")
    public ResponseEntity<Object> createArtist(@RequestBody Artist artist) {
        artistService.save(artist);
        return new ResponseEntity<>("Artist created", HttpStatus.CREATED);
    }

    @DeleteMapping("/artists/{id}")
    public ResponseEntity<Object> deleteArtist(@PathVariable("id") long id) {
        artistService.deleteById(id);
        return new ResponseEntity<>("Artist deleted", HttpStatus.OK);
    }

}
