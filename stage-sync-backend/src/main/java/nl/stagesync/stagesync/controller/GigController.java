package nl.stagesync.stagesync.controller;

import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.service.GigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class GigController {

    @Autowired
    GigService gigService;

    @GetMapping("/gigs")
    public ResponseEntity<Object> getGigs() {
        return ResponseEntity.ok(gigService.getGigs());
    }

    @GetMapping("/gigs/artists/{artist}")
    public ResponseEntity<Object> findGigsByArtist(@PathVariable("artist") String artist) {
        return ResponseEntity.ok(gigService.getGigsByArtist(artist));
    }

    @GetMapping("/gigs/{id}")
    public ResponseEntity<Object> getGigById(@PathVariable Long id) {
        return ResponseEntity.ok(gigService.getGigById(id));
    }

    @PostMapping("/gigs")
    public ResponseEntity<Object> createGig(@RequestBody Gig gig) {
        gigService.save(gig);
        return new ResponseEntity<>("Gig created", HttpStatus.CREATED);
    }

    @DeleteMapping("/gigs/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        gigService.deleteById(id);
        return new ResponseEntity<>("Gig deleted", HttpStatus.OK);
    }

}
