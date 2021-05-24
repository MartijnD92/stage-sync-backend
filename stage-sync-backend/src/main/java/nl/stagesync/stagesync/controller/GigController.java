package nl.stagesync.stagesync.controller;

import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.payload.request.CreateArtistRequest;
import nl.stagesync.stagesync.payload.request.CreateGigRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.service.GigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class GigController {

    private GigService gigService;

    @Autowired
    public void setGigService(GigService gigService) {
        this.gigService = gigService;
    }

    @GetMapping("/gigs")
    public ResponseEntity<Object> getGigs() {
        return ResponseEntity.ok(gigService.getGigs());
    }

    @GetMapping("/gigs/artists/{artist}")
    public ResponseEntity<Object> findGigsByArtist(@PathVariable("artist") String artist) {
        return ResponseEntity.ok(gigService.getGigsByArtistName(artist));
    }

    @GetMapping("/gigs/{id}")
    public ResponseEntity<Object> getGigById(@PathVariable long id) {
        return ResponseEntity.ok(gigService.getGigById(id));
    }

    @PostMapping("/gigs")
    public ResponseEntity<MessageResponse> createGig(@RequestBody CreateGigRequest createGigRequest) {
        return gigService.createGig(createGigRequest);
    }

    @DeleteMapping("/gigs/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable long id) {
        gigService.deleteById(id);
        return new ResponseEntity<>("Gig deleted", HttpStatus.OK);
    }

}
