package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;
    private final PhotoController photoController;

    public ProfileController(ProfileService profileService, PhotoController photoController) {
        this.profileService = profileService;
        this.photoController = photoController;
    }

    //MAPPINGS
    @GetMapping
    public ResponseEntity<List<Profile>> getProfiles() {
        return ResponseEntity.ok().body(profileService.getProfiles());
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<Profile> getProfile(@PathVariable String username) {
        return ResponseEntity.ok().body(profileService.getProfile(username));
    }

    @PutMapping("/{username}")
    public ResponseEntity<Object> updateProfile(@PathVariable String username, @RequestBody Profile newProfile) {
        profileService.updateProfile(username, newProfile);
        return ResponseEntity.ok().body("Je wijzigingen zijn opgeslagen");
    }

    @PostMapping("/{username}/photo")
    public void assignPhotoToStudent(@PathVariable String username,
                                     @RequestBody MultipartFile file) {
        FileUploadResponse photo = photoController.uploadFile(file);
        profileService.assignPhotoToProfile(photo.getFileName(), username);
    }

}
