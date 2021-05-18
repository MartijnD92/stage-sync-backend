package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.Gig;

import java.util.List;

public interface GigService {
    List<Gig> getGigs();
    List<Gig> getGigs(String venue, String artist);
    Gig getGigById(long id);
    List<Gig> getGigsByArtist(String artist);
    List<Gig> getGigsByVenue(String venue);
    void save(Gig gig);
    void deleteById(long id);

}
