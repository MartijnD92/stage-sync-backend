package nl.stagesync.stagesync.controller;

import nl.stagesync.stagesync.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/user")
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<Object> getUser(@RequestHeader Map<String, String> headers) {
//        return ResponseEntity.ok().body(userService.getUserByToken(headers.get("authorization")));
//    }
}
