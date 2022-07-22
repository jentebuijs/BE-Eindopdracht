package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.AuthDto;
import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
        UserOutputDto userOutputDto = userService.addUser(userInputDto);
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

    @PutMapping("/{userId}")
    public ResponseEntity<UserOutputDto> updateUser(@PathVariable Long userId, @RequestBody UserInputDto userInputDto) {
        UserOutputDto userOutputDto = userService.updateUser(userId, userInputDto);
        return ResponseEntity.ok().body(userOutputDto);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok().body("Gebruiker succesvol verwijderd");
    }

    @PostMapping("/{id}/photo")
    public void assignPhotoToStudent(@PathVariable("id") Long studentNumber,
                                     @RequestBody MultipartFile file) {
        FileUploadResponse photo = photoController.singleFileUpload(file);
        userService.assignPhotoToStudent(photo.getFileName(), studentNumber);
    }
}
