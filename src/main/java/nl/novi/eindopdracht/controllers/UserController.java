package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //MAPPINGS: inloggen, registreren, verwijderen ==> accountgerelateerd
    @GetMapping
    public ResponseEntity<Object> getUserBy(@RequestParam(value = "email", required = false) String email,
                                          @RequestParam(value = "username", required = false) String username) {
        UserOutputDto userOutputDto = new UserOutputDto();
        if (email != null) {
            userOutputDto = userService.getUserByEmail(email);
        }
        if (username != null) {
            userOutputDto = userService.getUserByUsername(username);
        }
        return ResponseEntity.ok().body(userOutputDto);
    }

    @PostMapping
    public ResponseEntity<UserOutputDto> addUser(@RequestBody UserInputDto userInputDto){
        UserOutputDto userOutputDto = userService.addUser(userInputDto);
        URI location = URI.create(userOutputDto.getUsername());
        return ResponseEntity.created(location).body(userOutputDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
