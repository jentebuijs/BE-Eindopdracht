package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.ProfileOutputDto;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.EAuthority;
import nl.novi.eindopdracht.models.FileUploadResponse;
import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.models.Request;
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

    public List<ProfileOutputDto> getProfiles() {
        String username = userService.getCurrentUsername();
        Optional<Profile> optionalProfile = profileRepository.findById(username);
        EAuthority role = optionalProfile.get().getRole();
        List<ProfileOutputDto> profileDtoList = new ArrayList<>();
        List<Profile> profileList;
        if ((role.getString().equals("Admin"))) {
            profileList = profileRepository.findAll();
        } else {
            profileList = profileRepository.getProfilesByIsActivatedIsTrueAndRoleIsNot(role);
            profileList = profileList.stream().filter(profile -> !profile.getRole().getString().equals("Admin"))
                    .collect(Collectors.toList());
        }
        for (Profile profile : profileList) {
            profileDtoList.add(profileToDto(profile));
        }
        return profileDtoList;
    }

    public ProfileOutputDto getProfile(String username) {
        Optional<Profile> possibleProfile = profileRepository.findById(username);
        if (possibleProfile.isEmpty()) {
            throw new RecordNotFoundException("Dit profiel is niet bekend");
        } else {
            return profileToDto(possibleProfile.get());
        }
    }

    public ProfileOutputDto updateProfile(String username, Profile newProfile) {
        Optional<Profile> possibleProfile = profileRepository.findById(username);
        if (possibleProfile.isEmpty()) {
            throw new RecordNotFoundException("Dit profiel is niet bekend");
        }
        Profile profileToUpdate = possibleProfile.get();
        profileToUpdate.setFirstName(newProfile.getFirstName());
        profileToUpdate.setLastName(newProfile.getLastName());
        profileToUpdate.setLevel(newProfile.getLevel());
        profileToUpdate.setFrequency(newProfile.getFrequency());
        profileToUpdate.setAboutMe(newProfile.getAboutMe());
        profileToUpdate.setActivated(newProfile.isActivated());
        profileRepository.save(profileToUpdate);
        return profileToDto(profileToUpdate);
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

//    public void assignRequestToProfile(Request request, String username) {}

    public ProfileOutputDto profileToDto(Profile profile) {
        ProfileOutputDto profileDto = new ProfileOutputDto();
        profileDto.setUsername(profile.getUsername());
        profileDto.setFirstName(profile.getFirstName());
        profileDto.setLastName(profile.getLastName());
        profileDto.setAge(profile.getAge());
        profileDto.setEmail(profile.getEmail());
        profileDto.setAboutMe(profile.getAboutMe());
        profileDto.setRole(profile.getRole().getString());
        profileDto.setFrequency(profile.getFrequency());
        profileDto.setLevel(profile.getLevel());
        profileDto.setPhoto(profile.getPhoto());
        profileDto.setActivated(profile.isActivated());
        return profileDto;
    }
}


