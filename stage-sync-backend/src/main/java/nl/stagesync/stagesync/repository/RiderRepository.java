package nl.stagesync.stagesync.repository;

import nl.stagesync.stagesync.model.Rider;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RiderRepository extends JpaRepository<Rider, Long> {

    List<Rider> findAllByArtistName(String artist);
}
