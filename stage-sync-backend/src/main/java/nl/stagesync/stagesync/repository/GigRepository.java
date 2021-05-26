package nl.stagesync.stagesync.repository;

import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.Gig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GigRepository extends JpaRepository<Gig, Long> {

    List<Gig> findAllByVenueAndArtist(String venue, String artist);
    List<Gig> findAllByVenue(String venue);
    List<Gig> findAllByArtistNameStartingWithIgnoreCase(String artist);


}
