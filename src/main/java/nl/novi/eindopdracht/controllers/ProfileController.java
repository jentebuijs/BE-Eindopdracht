package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.ProfileDto;
import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.services.PhotoService;
import nl.novi.eindopdracht.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    //MAPPINGS
    @GetMapping
    public ResponseEntity<?> getProfiles(@RequestParam(value = "username", required = false) Optional<String> username) {
        if (username.isPresent()) {
            return ResponseEntity.ok().body(profileService.getProfile(username.get()));
        } else {
            return ResponseEntity.ok().body(profileService.getProfiles());
        }

    }

    @PutMapping("/{username}")
    public ResponseEntity<Object> updateProfile(@PathVariable String username,
                                                @RequestBody(required = false) Optional<Profile> newProfile,
                                                @RequestParam(value = "active", required = false) Optional<Boolean> active) {
        if (newProfile.isPresent()) {
            profileService.updateProfile(username, newProfile.get());
        } else {
            profileService.updateProfileStatus(username, active.get());
        }
        return ResponseEntity.ok().body("Je wijzigingen zijn opgeslagen");
    }

}
