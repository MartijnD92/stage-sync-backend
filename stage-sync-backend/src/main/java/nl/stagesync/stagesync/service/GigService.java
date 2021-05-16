package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.Gig;

import java.util.List;
import java.util.Map;

public interface GigService {
    List<Gig> getGigs();
    List<Gig> getGigs(String venue, String artist);
    Gig getGigById(long id);
    List<Gig> getGigsVenueStartsWith(String venue);
    Long save(Map<String, String> fields);
    void deleteById(long id);

}
