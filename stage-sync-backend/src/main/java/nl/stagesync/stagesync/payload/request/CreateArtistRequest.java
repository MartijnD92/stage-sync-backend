package nl.stagesync.stagesync.payload.request;

import nl.stagesync.stagesync.model.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

public class CreateArtistRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String name;

    @Size(min = 1, max = 50)
    private String genre;

    @Size(min = 1, max = 50)
    private int price;

    private boolean hasSoundEngineer;

    private Set<User> users;

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

    public boolean hasSoundEngineer() {
        return hasSoundEngineer;
    }

    public void setHasSoundEngineer(boolean hasSoundEngineer) {
        this.hasSoundEngineer = hasSoundEngineer;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
