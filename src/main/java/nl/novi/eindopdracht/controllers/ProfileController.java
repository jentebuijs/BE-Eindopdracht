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
    @GetMapping("/buddies")
    public ResponseEntity<List<Profile>> getBuddyProfiles() {
        return ResponseEntity.ok().body(profileService.getBuddyProfiles());
    }

    @GetMapping("/students")
    public ResponseEntity<List<Profile>> getStudentProfiles() {
        return ResponseEntity.ok().body(profileService.getStudentProfiles());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok().body(profileService.getProfile(id));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<HttpStatus> updateProfile(@RequestBody Profile profileToUpdate) {
        return ResponseEntity.ok().build();
    }

}
