package nl.stagesync.stagesync.controller;

import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.model.User;
import nl.stagesync.stagesync.payload.request.CreateGigRequest;
import nl.stagesync.stagesync.payload.request.UpdateStatusRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.service.ArtistService;
import nl.stagesync.stagesync.service.GigService;
import nl.stagesync.stagesync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/gigs")
public class GigController {

    private GigService gigService;
    private UserService userService;
    private ArtistService artistService;

    @Autowired
    public void setGigService(GigService gigService) {
        this.gigService = gigService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public ResponseEntity<Object> getGigs() {
        return ResponseEntity.ok(gigService.getGigs());
    }

    @GetMapping("/{artist}")
    public ResponseEntity<Object> getGigsByQuery(@PathVariable("artist") String artist, Principal principal) {

        User currentUser = userService.getCurrentUser(principal);
        Set<Artist> currentUserArtists =  currentUser.getArtists();
        for (Artist currentUserArtist : currentUserArtists) {
            if (currentUserArtist.getName().equals(artist)) {
                return ResponseEntity.ok(artistService.getArtistByNameEager(artist));
            }
        }
        return new ResponseEntity<>("No gigs found for query: " + artist, HttpStatus.BAD_REQUEST);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> createGig(@RequestBody CreateGigRequest createGigRequest) {
        return gigService.createGig(createGigRequest);
    }

    @PatchMapping("/gig/{id}/update")
    public ResponseEntity<Object> updateStatusById(@PathVariable long id, @RequestBody UpdateStatusRequest status) {
        gigService.updateStatusById(id, status);
        return new ResponseEntity<>("Gig status has been updated", HttpStatus.OK);
    }

    @DeleteMapping("/gig/{id}/delete")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Object> deleteById(@PathVariable long id) {
        gigService.deleteById(id);
        return new ResponseEntity<>("Gig has been deleted", HttpStatus.OK);
    }

    @DeleteMapping("/{ids}/delete")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<Object> deleteGigByIds(@PathVariable("ids") List<String> ids) {
        ids.forEach(id -> {
            if (gigService.existsById(Integer.parseInt(id))) {
                gigService.deleteById(Integer.parseInt(id));
            }
        });
        return new ResponseEntity<>("Gigs have been deleted", HttpStatus.OK);
    }

}
