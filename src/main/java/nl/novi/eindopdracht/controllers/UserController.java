package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.AuthDto;
import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.models.FileUploadResponse;
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
    public ResponseEntity<UserOutputDto> signUp(@RequestBody UserInputDto userInputDto){
        UserOutputDto userOutputDto = userService.signUp(userInputDto);
        URI location = URI.create(userOutputDto.getUsername());
        return ResponseEntity.created(location).body(userOutputDto);
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

    @PutMapping("/{userId}")
    public ResponseEntity<UserOutputDto> updateUser(@PathVariable Long userId, @RequestBody UserInputDto userInputDto) {
        UserOutputDto userOutputDto = userService.updateUser(userId, userInputDto);
        return ResponseEntity.ok().body(userOutputDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("Gebruiker succesvol verwijderd");
    }

    @PostMapping("/{id}/photo")
    public void assignPhotoToStudent(@PathVariable("id") Long studentNumber,
                                     @RequestBody MultipartFile file) {
        FileUploadResponse photo = photoController.uploadFile(file);
        userService.assignPhotoToStudent(photo.getFileName(), studentNumber);
    }
}
