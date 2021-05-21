package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.model.User;
import nl.stagesync.stagesync.payload.request.CreateGigRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.repository.GigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class GigServiceImpl implements GigService {


    private GigRepository gigRepository;
    private ArtistServiceImpl artistService;

    @Autowired
    public void setGigRepository(GigRepository gigRepository) {
        this.gigRepository = gigRepository;
    }

    @Autowired
    public void setArtistService(ArtistServiceImpl artistService) {
        this.artistService = artistService;
    }

    @Override
    public List<Gig> getGigs() {
        return gigRepository.findAll();
    }

    @Override
    public List<Gig> getGigs(String venue, String artist) {
        if (!venue.isEmpty()) {
            if (!artist.isEmpty()) {
                return gigRepository.findAllByVenueAndArtist(venue, artist);
            }
            else {
                return gigRepository.findAllByVenue(venue);
            }
        }
        else {
            if (!artist.isEmpty()) {
                return gigRepository.findAllByArtistName(artist);
            }
            else {
                return gigRepository.findAll();
            }
        }
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
    public List<Gig> getGigsByArtist(String artist) {
        return gigRepository.findAllByArtistName(artist);
    }

    @Override
    public List<Gig> getGigsByVenue(String venue) {
        return gigRepository.findAllByVenue(venue);
    }

    @Override
    public ResponseEntity<MessageResponse> createGig(CreateGigRequest createGigRequest, long artistId) {

        Gig gig = new Gig(
                createGigRequest.getName(),
                createGigRequest.getVenue(),
                createGigRequest.getRoom(),
                createGigRequest.getLocation(),
                createGigRequest.getDate(),
                createGigRequest.getFee(),
                createGigRequest.getDuration(),
                createGigRequest.isConfirmed(),
                createGigRequest.getInvoiceStatus()
        );
        gig.setArtist(artistService.getArtistById(artistId));

        gigRepository.save(gig);

        return ResponseEntity.ok(new MessageResponse("Gig created successfully!"));
    }

    @Override
    public void deleteById(long id) {
        gigRepository.deleteById(id);
    }
}
