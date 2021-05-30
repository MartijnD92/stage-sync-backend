package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.NotAuthorizedException;
import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.User;
import nl.stagesync.stagesync.payload.request.UpdateUserRequest;
import nl.stagesync.stagesync.repository.UserRepository;
import nl.stagesync.stagesync.service.security.jwt.AuthEntryPointJwt;
import nl.stagesync.stagesync.service.security.jwt.JwtUtils;
import nl.stagesync.stagesync.utils.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public Collection<User> getAllUsers() {
        Collection<User> users = userRepository.findAll();
        if (users.isEmpty()) throw new RecordNotFoundException();
        return users;
    }

    @Override
    public User getUserByUsername(String username) {
        if (!userRepository.existsByUsername(username)) throw new RecordNotFoundException();
        return userRepository.findByUsername(username).get();
    }

    @Override
    public Map<String, String> getUserDetailsByUsername(String username) {
        if (!userRepository.existsByUsername(username)) throw new RecordNotFoundException();

        User user = userRepository.findByUsername(username).get();
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("username", user.getUsername());
        userDetails.put("firstName", user.getFirstName());
        userDetails.put("lastName", user.getLastName());
        userDetails.put("email", user.getEmail());
        return userDetails;
    }

    @Override
    public void updateUserByUsername(String username, Principal principal, UpdateUserRequest userRequest) {
        if (!userRepository.existsByUsername(username)) throw new RecordNotFoundException("User " + username + " does not exist.");
        User user = userRepository.findByUsername(username).get();

        try {
            if (!userRequest.getPassword().isEmpty() && !userRequest.getPasswordConfirmation().isEmpty()) {
                user.setPassword(encoder.encode(userRequest.getPassword()));
            }
        }
        catch(NullPointerException e) {
            user.setPassword(user.getPassword());
        }

        if (!userRequest.getEmail().isEmpty()) {
            user.setEmail(userRequest.getEmail());
        }
        if (!userRequest.getFirstName().isEmpty()) {
            user.setFirstName(userRequest.getFirstName());
        }
        if (!userRequest.getLastName().isEmpty()) {
            user.setLastName(userRequest.getLastName());
        }
        userRepository.save(user);
    }

    @Override
    public void uploadProfilePicture(MultipartFile multipartFile, Principal principal) throws IOException {
        User user = getUserByUsername(principal.getName());
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String uploadDir = "profilepictures/" + user.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        user.setProfilePicture(fileName);
        userRepository.save(user);
    }


    @Override
    public String getProfilePicture(Principal principal) throws IOException {

        User user = getUserByUsername(principal.getName());

        String fileName = user.getProfilePicture();

        Path path = Paths.get("profilepictures/" + user.getId() + "/" + fileName);
        if(path.endsWith("null")) path = Paths.get("profilepictures/default.jpg");
        ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));
        return Base64.getEncoder().withoutPadding().encodeToString(resource.getByteArray());
    }

    @Override
    public User getCurrentUser(Principal principal) {
        if (principal.getName() == null) throw new NotAuthorizedException();
        return getUserByUsername(principal.getName());
    }

}
