package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.controller.ArtistController;
import nl.stagesync.stagesync.exception.NotAuthorizedException;
import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.Rider;
import nl.stagesync.stagesync.model.User;
import nl.stagesync.stagesync.payload.request.CreateArtistRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.repository.ArtistRepository;
import nl.stagesync.stagesync.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.*;

@Service
public class ArtistServiceImpl implements ArtistService {

    private static final Logger LOG = LoggerFactory.getLogger(ArtistServiceImpl.class);


    private ArtistRepository artistRepository;
    private UserServiceImpl userService;
    private RiderServiceImpl riderService;

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRiderService(RiderServiceImpl riderService) {
        this.riderService = riderService;
    }

    @Override
    public Set<Artist> getAllArtists(Principal principal) {
        if (principal.getName() == null) throw new NotAuthorizedException();
        User currentUser = userService.getUserByUsername(principal.getName());
        return currentUser.getArtists();
    }

    @Override
    public Artist getArtistById(long id) {
        Optional<Artist> artist = artistRepository.findById(id);
        if (artist.isEmpty()) {
            throw new RecordNotFoundException("No artist with id " + id);
        }
        return artist.get();
    }


    @Override
    public Artist getArtistByName(String name) {
        Optional<Artist> artist = artistRepository.findByName(name);
        LOG.info("hier ook nog???");
        if (artist.isEmpty()) {
            throw new RecordNotFoundException("No artist with name " + name);
        }
        return artist.get();
    }


    @Override
    public List<Artist> getArtistsNameStartsWith(String name) {
        return artistRepository.findAllByNameStartingWithIgnoreCase(name);
    }

        @Override
    public void createArtist(CreateArtistRequest createArtistRequest, Principal principal) {

        User currentUser = userService.getUserByUsername(principal.getName());

        Artist artist;
        if (artistRepository.findByName(createArtistRequest.getName()).isEmpty()) {
            artist = new Artist(
                    createArtistRequest.getName(),
                    createArtistRequest.getGenre(),
                    createArtistRequest.getPrice(),
                    createArtistRequest.hasSoundEngineer(),
                    createArtistRequest.getBio(),
                    createArtistRequest.getUsers()
            );
        }
        else {
            artist = getArtistByName(createArtistRequest.getName());
        }

        Set<User> users = new HashSet<>();
        if (artist.getUsers() != null) {
            users.addAll(artist.getUsers());
        }
        users.add(currentUser);
        artist.setUsers(users);

        artistRepository.save(artist);
    }

    @Override
    public void deleteById(long id) {
        if (artistRepository.existsById(id)) {
            artistRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No artist with id " + id);
        }
    }
}
