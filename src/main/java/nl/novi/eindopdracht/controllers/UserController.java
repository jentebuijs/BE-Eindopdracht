package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.AuthDto;
import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PhotoController photoController;

    public UserController(UserService userService, PhotoController photoController) {
        this.userService = userService;
        this.photoController = photoController;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody UserInputDto userInputDto){
        User user = userService.signUp(userInputDto);
        URI location = URI.create(user.getUsername());
        return ResponseEntity.created(location).body(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody AuthDto authDto) {
        String token = userService.signIn(authDto);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(token);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserOutputDto> getUser(@PathVariable String username) {
        UserOutputDto userOutputDto = userService.getUser(username);
        return ResponseEntity.ok().body(userOutputDto);
    }

    @PutMapping("/{username}")
    public ResponseEntity<UserOutputDto> updateUser(@PathVariable String username, @RequestBody UserInputDto userInputDto) {
        UserOutputDto userOutputDto = userService.updateUser(username, userInputDto);
        return ResponseEntity.ok().body(userOutputDto);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().body("Gebruiker succesvol verwijderd");
    }

    @PostMapping("/{username}/photo")
    public void assignPhotoToStudent(@PathVariable String username,
                                     @RequestBody MultipartFile file) {
        FileUploadResponse photo = photoController.uploadFile(file);
        userService.assignPhotoToStudent(photo.getFileName(), username);
    }
}
