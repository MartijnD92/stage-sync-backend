package nl.stagesync.stagesync.repository;

import nl.stagesync.stagesync.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findAllByNameStartingWithIgnoreCase(String name);
    Optional<Artist> findByName(String name);
}