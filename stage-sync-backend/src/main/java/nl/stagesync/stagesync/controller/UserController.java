package nl.stagesync.stagesync.controller;

import nl.stagesync.stagesync.model.User;
import nl.stagesync.stagesync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allusers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<User> userAccess() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{username}")
    @PreAuthorize("hasRole('USER') and #username == authentication.principal.username")
    public ResponseEntity<Object> getUser(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }
}
