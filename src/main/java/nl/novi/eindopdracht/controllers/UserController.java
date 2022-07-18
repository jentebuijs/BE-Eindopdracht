package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.services.UserService;
import org.apache.coyote.Response;
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

    //MAPPINGS: inloggen, registreren, aanpassen, verwijderen ==> accountgerelateerd

    @PostMapping("/new")
    public ResponseEntity<UserOutputDto> addUser(@RequestBody UserInputDto userInputDto){
        UserOutputDto userOutputDto = userService.addUser(userInputDto);
        URI location = URI.create(userOutputDto.getUsername());
        return ResponseEntity.created(location).body(userOutputDto);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserOutputDto> updateUser(@PathVariable Long userId, @RequestBody UserInputDto userInputDto) {
        UserOutputDto userOutputDto = userService.updateUser(userId, userInputDto);
        return ResponseEntity.ok().body(userOutputDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
