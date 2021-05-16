package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.Gig;
import nl.stagesync.stagesync.repository.GigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GigServiceImpl implements GigService{

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
                return gigRepository.findAllByArtist(artist);
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
    public List<Gig> getGigsVenueStartsWith(String name) {
        return gigRepository.findAllByVenueStartingWith(name);
    }

    @Override
    public Long save(Map<String, String> fields) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(fields.get("date"), formatter);
        Gig newGig = new Gig();
        newGig.setDate(dateTime);
        Gig storedGig = gigRepository.save(newGig);
        return storedGig.getId();
    }

    @Override
    public void deleteById(long id) {
        gigRepository.deleteById(id);
    }
}
