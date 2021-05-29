package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.ForbiddenException;
import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.EConfirmationStatus;
import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.payload.request.CreateGigRequest;
import nl.stagesync.stagesync.payload.request.UpdateStatusRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.repository.GigRepository;
import nl.stagesync.stagesync.service.security.jwt.AuthEntryPointJwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GigServiceImpl implements GigService {

    private static final Logger LOG = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    private GigRepository gigRepository;
    private ArtistService artistService;

    @Autowired
    public void setGigRepository(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Autowired
    public void setArtistService(ArtistService artistService) {
        this.artistService = artistService;
    }

    @Override
    public List<Gig> getGigs() {
        return gigRepository.findAll();
    }

    @Override
    public Gig getGigById(long id) {
        if (!gigRepository.existsById(id)) throw new RecordNotFoundException("Gig with id " + id + " not found!");
        return gigRepository.findById(id);
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
                createGigRequest.getArtistName()
        );

        Artist artist = artistService.getArtistByName(gig.getArtistName());
        gig.setArtist(artist);

        gigRepository.save(gig);

        return ResponseEntity.ok(new MessageResponse("Gig created successfully!"));
    }

    @Override
    public boolean existsById(long id) {
        return gigRepository.existsById(id);
    }


    @Override
    public void deleteById(long id) {
        gigRepository.deleteById(id);
    }

    @Override
    public void updateStatusById(long id, UpdateStatusRequest status) {
        EConfirmationStatus newStatus = status.getConfirmationStatus();
        LOG.info(newStatus.toString());
        Gig gig = getGigById(id);
        EConfirmationStatus[] confirmStatuses = EConfirmationStatus.values();
        for (EConfirmationStatus confirmStatus : confirmStatuses) {
            LOG.info(confirmStatus.toString());
            if (confirmStatus.equals(newStatus)) {
                gig.setConfirmationStatus(confirmStatus);
                gigRepository.save(gig);
                return;
            }
        }
        throw new RecordNotFoundException("Unable to find gig with id: " + id);
    }
}
