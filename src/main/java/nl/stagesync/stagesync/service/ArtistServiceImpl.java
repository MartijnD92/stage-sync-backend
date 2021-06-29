package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.NotAuthorizedException;
import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.Artist;
import nl.stagesync.stagesync.model.User;
import nl.stagesync.stagesync.payload.request.CreateArtistRequest;
import nl.stagesync.stagesync.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.*;

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
        User currentUser = userService.getCurrentUser(principal);
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
        if (artist.isEmpty()) {
            throw new RecordNotFoundException("No artist with name " + name);
        }
        return artist.get();
    }


    @Override
    public List<Artist> getArtistsByNameEager(String name) {
        return artistRepository.findByNameContainingIgnoreCase(name);
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
