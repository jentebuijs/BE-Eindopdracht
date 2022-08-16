package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.ProfileDto;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.EAuthority;
import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.repositories.FileUploadRepository;
import nl.novi.eindopdracht.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ProfileDto> getProfiles() {
        String username = userService.getCurrentUsername();
        Optional<Profile> optionalProfile = profileRepository.findById(username);
        EAuthority role = optionalProfile.get().getRole();
        List<ProfileDto> profileDtoList = new ArrayList<>();
        List<Profile> profileList;
        if ((role.getString().equals("Admin"))) {
            profileList = profileRepository.findAll();
        } else {
            profileList = profileRepository.getProfilesByActiveIsTrueAndRoleIsNot(role);
            profileList = profileList.stream().filter(profile -> !profile.getRole().getString().equals("Admin"))
                    .collect(Collectors.toList());
        }
        for (Profile profile : profileList) {
            profileDtoList.add(profileToDto(profile));
        }
        return profileDtoList;
    }

    public ProfileDto getProfile(String username) {
        Optional<Profile> possibleProfile = profileRepository.findById(username);
        if (possibleProfile.isEmpty()) {
            throw new RecordNotFoundException("Dit profiel is niet bekend");
        } else {
            return profileToDto(possibleProfile.get());
        }
    }

    public void updateProfile(String username, Profile newProfile) {
        Optional<Profile> possibleProfile = profileRepository.findById(username);
        if (possibleProfile.isEmpty()) {
            throw new RecordNotFoundException("Dit profiel is niet bekend");
        }
        Profile profileToUpdate = possibleProfile.get();
        profileToUpdate.setFirstName(newProfile.getFirstName());
        profileToUpdate.setLastName(newProfile.getLastName());
        profileToUpdate.setLevel(newProfile.getLevel());
//        profileToUpdate.setFrequency(newProfile.getFrequency());
        profileToUpdate.setAboutMe(newProfile.getAboutMe());
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

    public ProfileDto profileToDto(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setUsername(profile.getUsername());
        profileDto.setFirstName(profile.getFirstName());
        profileDto.setLastName(profile.getLastName());
        profileDto.setDob(profile.getDob());
        profileDto.setAge(profile.getAge());
        profileDto.setEmail(profile.getEmail());
        profileDto.setAboutMe(profile.getAboutMe());
        profileDto.setRole(profile.getRole().getString());
        profileDto.setFrequency(profile.getFrequency());
        profileDto.setLevel(profile.getLevel());
        profileDto.setPhoto(profile.getPhoto());
        return profileDto;
    }

    public void updateProfileStatus(String username, Boolean active) {
        Profile updatedProfile = profileRepository.findById(username).get();
        updatedProfile.setActive(active);
        profileRepository.save(updatedProfile);
    }
}


