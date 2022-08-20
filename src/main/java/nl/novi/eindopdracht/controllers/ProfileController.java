package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.ProfileOutputDto;
import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.services.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<ProfileOutputDto> updateProfile(@PathVariable String username, @RequestBody Profile newProfile) {
            return ResponseEntity.ok().body(profileService.updateProfile(username, newProfile));
    }
}
