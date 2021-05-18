package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

public interface UserService {
    Collection<User> getAllUsers();
    User getUserByUsername(String username);
}
