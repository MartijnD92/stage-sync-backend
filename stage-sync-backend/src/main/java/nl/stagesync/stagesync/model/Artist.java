package nl.stagesync.stagesync.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "artist")
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String genre;

    @Column
    private int price;

    @Column(name = "has_sound_engineer")
    private boolean hasSoundEngineer;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "artist"
    )
    @JsonIgnoreProperties("artist")
    private List<Rider> riders;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "artist"
    )
    @JsonIgnoreProperties("artist")
    private List<Gig> gigs;

    @ManyToMany
    @JoinTable (name = "artist_user",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> users;

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", hasSoundEngineer=" + hasSoundEngineer +
                ", riders=" + riders +
                ", gigs=" + gigs +
                ", users=" + users +
                '}';
    }

    public Artist() {
    }

    public Artist(String name, String genre, int price, boolean hasSoundEngineer, Set<User> users) {
        this.name = name;
        this.genre = genre;
        this.price = price;
        this.hasSoundEngineer = hasSoundEngineer;
        this.users = users;
    }

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

    public List<Gig> getGigs() {
        return gigs;
    }

    public void setGigs(List<Gig> gigs) {
        this.gigs = gigs;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public boolean hasSoundEngineer() {
        return hasSoundEngineer;
    }

    public void setHasSoundEngineer(boolean hasSoundEngineer) {
        this.hasSoundEngineer = hasSoundEngineer;
    }

    public List<Rider> getRiders() {
        return riders;
    }

    public void setRiders(List<Rider> riders) {
        this.riders = riders;
    }
}
