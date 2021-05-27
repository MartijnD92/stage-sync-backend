package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.payload.request.CreateGigRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GigService {
    List<Gig> getGigs();
    Gig getGigById(long id);
    List<Gig> getGigsByArtistName(String artist);
    List<Gig> getGigsByVenue(String venue);
    ResponseEntity<MessageResponse> createGig(CreateGigRequest createGigRequest);
    boolean existsById(long id);
    void deleteById(long id);

}
