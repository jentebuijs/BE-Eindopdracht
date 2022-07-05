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

    //MAPPINGS: overview (+filteren/sorteren), specifiek profiel, update profiel, uploaden foto?, wijzigen foto?

    @GetMapping
    public ResponseEntity<List<Profile>> getProfiles() {
        return ResponseEntity.ok().body(profileService.getProfiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok().body(profileService.getProfile(id));
    }

//    @PutMapping("/{username}")
//    public ResponseEntity<HttpStatus> updateProfile(@RequestBody Profile profile) {
//    }

}
