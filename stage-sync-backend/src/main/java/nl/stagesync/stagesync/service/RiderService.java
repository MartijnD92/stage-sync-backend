package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.Rider;
import org.springframework.web.multipart.MultipartFile;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface RiderService {

    /**
     * Add an array of files
     *
     * @param multipartFiles
     */
    void addRiders(MultipartFile[] multipartFiles) throws NoSuchAlgorithmException;

    List<Rider> getAllRidersByArtistName(String artist);
    List<Rider> getAllRiders();
}
