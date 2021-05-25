package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.payload.request.CreateGigRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.repository.GigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GigServiceImpl implements GigService {

    private GigRepository gigRepository;

    @Autowired
    public void setGigRepository(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Override
    public List<Gig> getGigs() {
       return  gigRepository.findAll();
    }

    @Override
    public Gig getGigById(long id) {
        Optional<Gig> gig = gigRepository.findById(id);
        if (gig.isEmpty()) {
            throw new RecordNotFoundException("No gig with id " + id);
        }
        return gig.get();
    }

    @Override
    public List<Gig> getGigsByArtistName(String artist) {
        return gigRepository.findAllByArtistName(artist);
    }

    @Override
    public List<Gig> getGigsByVenue(String venue) {
        return gigRepository.findAllByVenue(venue);
    }

    @Override
    public ResponseEntity<MessageResponse> createGig(CreateGigRequest createGigRequest) {

        Gig gig = new Gig(
                createGigRequest.getName(),
                createGigRequest.getVenue(),
                createGigRequest.getRoom(),
                createGigRequest.getLocation(),
                createGigRequest.getDate(),
                createGigRequest.getFee(),
                createGigRequest.getDuration(),
                createGigRequest.getTicketsTotal(),
                createGigRequest.getConfirmationStatus(),
                createGigRequest.getInvoiceStatus(),
                createGigRequest.getArtist()
        );

        gigRepository.save(gig);

        return ResponseEntity.ok(new MessageResponse("Gig created successfully!"));
    }

    @Override
    public void deleteById(long id) {
        gigRepository.deleteById(id);
    }
}
