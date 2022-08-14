package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.Authority;
import nl.novi.eindopdracht.models.EAuthority;
import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.repositories.FileUploadRepository;
import nl.novi.eindopdracht.repositories.ProfileRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final FileUploadRepository fileUploadRepository;
    private final UserService userService;

    public ProfileService(ProfileRepository profileRepository, FileUploadRepository fileUploadRepository, UserService userService) {
        this.profileRepository = profileRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.userService = userService;
    }

    public List<Profile> getProfiles() {
        String username = userService.getCurrentUsername();
        Optional<Profile> optionalProfile = profileRepository.findById(username);
        EAuthority role = optionalProfile.get().getRole();
        return profileRepository.getProfilesByRoleIsNot(role);


//        if (authorityList.size() > 1) {
//            return profileRepository.findAll();
//        } else {
//            Object[] authorityArray = authorityList.toArray();
//            return profileRepository.getProfilesByRoleContaining(authorityArray[0]toString());
//        }
    }

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
        }
        Profile profileToUpdate = possibleProfile.get();
        if (!newProfile.getFirstName().isEmpty()) {
            profileToUpdate.setFirstName(newProfile.getFirstName());
        }
        if (!newProfile.getLastName().isEmpty()) {
            profileToUpdate.setLastName(newProfile.getLastName());
        }
        if (newProfile.getDob() != null) {
            profileToUpdate.setDob(newProfile.getDob());
        }
        profileToUpdate.setLevel(newProfile.getLevel());
        profileToUpdate.setFrequency(newProfile.getFrequency());
        if (!newProfile.getAboutMe().isEmpty()) {
            profileToUpdate.setAboutMe(newProfile.getAboutMe());
        }
        profileRepository.save(profileToUpdate);
    }

    public void assignPhotoToProfile(String fileName, String username) {
        Optional<Profile> optionalProfile = profileRepository.findById(username);
        Optional<FileUploadResponse> fileUploadResponse = fileUploadRepository.findByFileName(fileName);
        if (optionalProfile.isPresent() && fileUploadResponse.isPresent()) {
            FileUploadResponse photo = fileUploadResponse.get();
            Profile profile = optionalProfile.get();
            profile.setPhoto(photo);
            profileRepository.save(profile);
        }
    }
}


