package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.User;
import nl.stagesync.stagesync.repository.UserRepository;
import nl.stagesync.stagesync.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}
