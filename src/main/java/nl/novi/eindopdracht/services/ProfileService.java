package nl.novi.eindopdracht.services;

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

//    public void createProfileFromUser(User user) {
//        Profile profile = new Profile();
//        profile.setUser(user);
//        profile.setIsStudent(user.getIsStudent());
//        profileRepository.save(profile);
//    }

    public List<Profile> getAllProfiles() { return profileRepository.findAll(); }

//    public List<Profile> getBuddyProfiles() { return profileRepository.getAllByIsStudentIsFalse(); }
//
//    public List<Profile> getStudentProfiles() {
//        return profileRepository.getAllByIsStudentIsTrue();
//    }

    public Profile getProfile(String username) {
        Optional<Profile> possibleProfile = profileRepository.findById(username);
        if (possibleProfile.isEmpty()) {
            throw new RecordNotFoundException("Dit profiel is niet bekend");
        } else {
            return possibleProfile.get();
        }
    }

    public void updateProfile(String username, Profile newProfile) {
        Optional<Profile> possibleProfile = profileRepository.findById(username);
        if (possibleProfile.isEmpty()) {
            throw new RecordNotFoundException("Dit profiel is niet bekend");
        }   Profile profileToUpdate = possibleProfile.get();
            profileToUpdate.setFirstName(newProfile.getFirstName());
            profileToUpdate.setLastName(newProfile.getLastName());
            profileToUpdate.setDob(newProfile.getDob());
            profileToUpdate.setLevel(newProfile.getLevel());
            profileToUpdate.setContactIntensity(newProfile.getContactIntensity());
            profileToUpdate.setAboutMe(newProfile.getAboutMe());
            profileRepository.save(profileToUpdate);
        }
    }


