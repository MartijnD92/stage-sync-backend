package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.payload.request.CreateGigRequest;
import nl.stagesync.stagesync.payload.request.UpdateStatusRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GigService {
    List<Gig> getGigs();
    ResponseEntity<MessageResponse> createGig(CreateGigRequest createGigRequest);
    boolean existsById(long id);
    void deleteById(long id);
    void updateStatusById(long id, UpdateStatusRequest status);
    Gig getGigById(long id);
}
