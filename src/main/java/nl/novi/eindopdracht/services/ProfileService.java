package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.repositories.FileUploadRepository;
import nl.novi.eindopdracht.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final FileUploadRepository fileUploadRepository;

    public ProfileService(ProfileRepository profileRepository, FileUploadRepository fileUploadRepository) {
        this.profileRepository = profileRepository;
        this.fileUploadRepository = fileUploadRepository;
    }

    public List<Profile> getProfiles() {
        return profileRepository.findAll();
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
        }   Profile profileToUpdate = possibleProfile.get();
            profileToUpdate.setFirstName(newProfile.getFirstName());
            profileToUpdate.setLastName(newProfile.getLastName());
            profileToUpdate.setDob(newProfile.getDob());
            profileToUpdate.setLevel(newProfile.getLevel());
            profileToUpdate.setFrequency(newProfile.getFrequency());
            profileToUpdate.setAboutMe(newProfile.getAboutMe());
            profileRepository.save(profileToUpdate);
        }

    public void assignPhotoToProfile(String fileName, String username) {
        Optional<Profile> optionalProfile = profileRepository.findById(username);
        Optional<FileUploadResponse> fileUploadResponse = fileUploadRepository.findByFileName(fileName);
        if (optionalProfile.isPresent() && fileUploadResponse.isPresent()) {
            FileUploadResponse photo = fileUploadResponse.get();
            Profile profile = optionalProfile.get();
            profile.setFileUploadResponse(photo);
            profileRepository.save(profile);
        }
    }
    }


