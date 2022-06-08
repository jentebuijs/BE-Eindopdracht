package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<User> getUserBy(@RequestParam(value = "email", required = false) String email,
                                          @RequestParam(value = "username", required = false) String username) {
        if (!email.isEmpty()) {
            return ResponseEntity.ok().body(userService.getUserByEmail(email));
        }
        if (!username.isEmpty()) {
            return ResponseEntity.ok().body(userService.getUserByUsername(username));
        }
    }

    @PostMapping
    public ResponseEntity<UserOutputDto> addUser(@RequestBody UserInputDto userInputDto){
        User user = userService.addUser(userInputDto);
        URI location = URI.create("/users/" + user.getUsername());
        return ResponseEntity.created(location).body(userService.fromUser(user));
    }
}
