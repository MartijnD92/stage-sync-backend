package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.User;
import nl.stagesync.stagesync.payload.request.CreateArtistRequest;
import nl.stagesync.stagesync.payload.response.MessageResponse;
import nl.stagesync.stagesync.repository.ArtistRepository;
import nl.stagesync.stagesync.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArtistServiceImpl implements ArtistService {

    private ArtistRepository artistRepository;
    private UserServiceImpl userService;

    @Autowired
    public void setArtistRepository(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
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
        return artistRepository.findAllByNameStartingWith(name);
    }

    @Override
    public ResponseEntity<MessageResponse> createArtist(CreateArtistRequest createArtistRequest, Principal principal) {

        User currentUser = userService.getUserByUsername(principal.getName());

        Artist artist = new Artist(
                createArtistRequest.getName(),
                createArtistRequest.getGenre(),
                createArtistRequest.getPrice(),
                createArtistRequest.hasSoundEngineer(),
                createArtistRequest.getUsers()
        );

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
