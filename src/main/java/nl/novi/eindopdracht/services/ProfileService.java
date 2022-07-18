package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void profileFromUser(User user) {
        Profile profile = new Profile();
        profile.setUser(user);
        profile.setIsStudent(user.getIsStudent());
        profileRepository.save(profile);
    }

    public List<Profile> getBuddyProfiles() { return profileRepository.getAllByIsStudentIsFalse(); }

    public List<Profile> getStudentProfiles() {
        return profileRepository.getAllByIsStudentIsTrue();
    }

    public Profile getProfile(Long id) {
        Optional<Profile> possibleProfile = profileRepository.findById(id);
        if (possibleProfile.isEmpty()) {
            throw new RecordNotFoundException("Dit profiel is niet bekend");
        } else {
            return possibleProfile.get();
        }
    }

    public void updateProfile(Long id, Profile profileToUpdate) {
        Optional<Profile> possibleProfile = profileRepository.findById(id);
        if (possibleProfile.isEmpty()) {
            throw new RecordNotFoundException("Dit profiel is niet bekend");
        } else {
            Profile profileToDelete = possibleProfile.get();
            profileRepository.delete(profileToDelete);
            profileRepository.save(profileToUpdate);
        }
    }

}
