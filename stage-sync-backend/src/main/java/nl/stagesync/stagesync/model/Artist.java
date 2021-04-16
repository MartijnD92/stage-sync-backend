package nl.stagesync.stagesync.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

//    @Column
//    private enum Type {
//        MUSIC, COMEDY, DANCE
//    }

    @Column
    private String genre;

    @Column
    private int price;

    @Column
    private boolean hasSoundEngineer;

    @OneToMany
    List<Show> shows;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isHasSoundEngineer() {
        return hasSoundEngineer;
    }

    public void setHasSoundEngineer(boolean hasSoundEngineer) {
        this.hasSoundEngineer = hasSoundEngineer;
    }

    public List<Show> getShows() {
        return shows;
    }

    public void setShows(List<Show> shows) {
        this.shows = shows;
    }
}
