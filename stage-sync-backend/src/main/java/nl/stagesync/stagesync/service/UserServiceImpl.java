package nl.stagesync.stagesync.service;

import nl.stagesync.stagesync.exception.RecordNotFoundException;
import nl.stagesync.stagesync.model.User;
import nl.stagesync.stagesync.repository.UserRepository;
import nl.stagesync.stagesync.service.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

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

}
