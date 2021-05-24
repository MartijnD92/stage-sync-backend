package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.Rider;
import nl.stagesync.stagesync.repository.RiderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class RiderServiceImpl implements RiderService {

    private RiderRepository riderRepository;

    @Autowired
    public void setRiderRepository(RiderRepository riderRepository) {
        this.riderRepository = riderRepository;
    }

    @Override
    public List<Rider> getAllRiders() {
        return riderRepository.findAll();
    }

    @Override
    public List<Rider> getAllRidersByArtistName(String artist) {
        return riderRepository.findAllByArtistName(artist);
    }

    @Override
    @Transactional
    public void addRiders(MultipartFile[] multipartFiles) throws NoSuchAlgorithmException {
        for (MultipartFile multipartFile : multipartFiles) {
            create(multipartFile);
        }
    }

    private void create(MultipartFile multipartFile) throws NoSuchAlgorithmException {
        Rider rider = new Rider();
        rider.setName(multipartFile.getOriginalFilename());
        rider.setMediaType(multipartFile.getContentType());
        rider.setSize(multipartFile.getSize());
        rider.setHash();
        riderRepository.save(rider);
    }
}
