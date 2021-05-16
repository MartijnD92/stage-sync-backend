package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.Artist;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtists();
    Artist getArtistById(long id);
    List<Artist> getArtistsNameStartsWith(String name);
    void save(Artist artist);
    void deleteById(long id);
}
