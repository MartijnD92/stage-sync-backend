package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Collection;
import java.util.Map;

public interface UserService {
    Collection<User> getAllUsers();
    User getUserByUsername(String username);
    void uploadProfilePicture(MultipartFile multipartFile, Principal principal) throws IOException;
    String getProfilePicture(Principal principal) throws IOException;
    Map<String, String> getUserDetailsByUsername(String username);
}
