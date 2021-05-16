package nl.stagesync.stagesync.controller;

import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.service.GigService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class GigController {

    @Autowired
    GigService gigService;

    @GetMapping("/gigs")
    public ResponseEntity<Object> getGigs() {
        return ResponseEntity.ok(gigService.getGigs());
    }

//    @GetMapping("/gigs/artists/{artist}")
//    public ResponseEntity<Object> getGigs(@PathVariable("artist") String artist) {
//
//    }

    @GetMapping("/gigs/{id}")
    public ResponseEntity<Object> getGigById(@PathVariable Long id) {
        return ResponseEntity.ok(gigService.getGigById(id));
    }

    @PostMapping("/gigs")
    public ResponseEntity<Object> createGig(@RequestBody Map<String, String> fields) {
        gigService.save(fields);
        return new ResponseEntity<>("Gig created", HttpStatus.CREATED);
    }

    @DeleteMapping("/gigs/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        gigService.deleteById(id);
        return new ResponseEntity<>("Gig deleted", HttpStatus.OK);
    }

}
