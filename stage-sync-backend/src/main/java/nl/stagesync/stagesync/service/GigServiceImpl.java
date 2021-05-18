package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.repository.GigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class GigServiceImpl implements GigService {

    @Autowired
    GigRepository gigRepository;

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
    public void save(Gig gig) {
        gigRepository.save(gig);
    }

    @Override
    public void deleteById(long id) {
        gigRepository.deleteById(id);
    }
}
