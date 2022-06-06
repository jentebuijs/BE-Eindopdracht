package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> addUser(@RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok().build();
    }
}
