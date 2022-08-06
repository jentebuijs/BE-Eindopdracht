package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.services.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
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

}
