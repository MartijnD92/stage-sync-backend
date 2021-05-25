package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.NotAuthorizedException;
import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.Rider;
import nl.stagesync.stagesync.model.User;
import nl.stagesync.stagesync.payload.request.CreateArtistRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.repository.ArtistRepository;
import nl.stagesync.stagesync.repository.UserRepository;
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
    public List<Artist> getArtistsNameStartsWith(String name) {
        return artistRepository.findAllByNameStartingWithIgnoreCase(name);
    }

        @Override
    public ResponseEntity<MessageResponse> createArtist(CreateArtistRequest createArtistRequest, Principal principal) {

        User currentUser = userService.getUserByUsername(principal.getName());

        Artist artist;
        if (artistRepository.findArtistByName(createArtistRequest.getName()) == null) {
            artist = new Artist(
                    createArtistRequest.getName(),
                    createArtistRequest.getGenre(),
                    createArtistRequest.getPrice(),
                    createArtistRequest.hasSoundEngineer(),
                    createArtistRequest.getBio(),
                    createArtistRequest.getUsers()
            );
        } else {
            artist = artistRepository.findArtistByName(createArtistRequest.getName());
        }

        Set<User> users = new HashSet<>();
        if (artist.getUsers() != null) {
            users.addAll(artist.getUsers());
        }
        users.add(currentUser);
        artist.setUsers(users);

        artistRepository.save(artist);

        return ResponseEntity.ok(new MessageResponse("Artist created successfully!"));
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
