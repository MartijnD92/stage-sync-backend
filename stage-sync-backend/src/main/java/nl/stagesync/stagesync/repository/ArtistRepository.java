package nl.stagesync.stagesync.repository;

import nl.stagesync.stagesync.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    List<Artist> findAllByNameStartingWith(String name);
}
