package nl.stagesync.stagesync.repository;

import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.Gig;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GigRepository extends JpaRepository<Gig, Long> {

    List<Gig> findByNameContainingIgnoreCase(String gig);
    List<Gig> findByArtistNameContainingIgnoreCase(String artist);
    Optional<Artist> findByArtistName(String name);
    boolean existsById(long id);
    Gig findById(long id);
}
