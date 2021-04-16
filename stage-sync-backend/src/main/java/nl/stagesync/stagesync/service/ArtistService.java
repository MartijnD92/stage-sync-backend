package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.Artist;

import java.util.List;

public interface ArtistService {

    public List<Artist> getAllArtists();
    public Artist getArtist(long id);
    public List<Artist> getArtistsNameStartsWith(String name);
    public void save(Artist artist);
    public void deleteById(long id);
}
