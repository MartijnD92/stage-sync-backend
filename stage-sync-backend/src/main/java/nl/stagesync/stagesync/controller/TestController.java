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
@RequestMapping("/api/test")
public class TestController {

    private final UserService userService;

    @Autowired
    public TestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allusers")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Collection<User> userAccess() {
        return userService.getAllUsers();
    }

    // DIT WERKT!!!
    @GetMapping("/user/{username}")
    @PreAuthorize("hasRole('USER') && #username == authentication.principal.username")
    public ResponseEntity<Object> getUser(@PathVariable String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }
}
