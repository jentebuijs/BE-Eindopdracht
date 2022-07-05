package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    //aanmaken profiel (aangeroepen vanuit addUser() in userService)
    public void profileFromUser(User user) {
        Profile profile = new Profile();
//        profile.setId(user.getId());
        profile.setIsStudent(user.getIsStudent());
        profile.setUser(user);
        profileRepository.save(profile);
    }

    @Override
    public List<Profile> getProfiles() {
        return profileRepository.findAll();
    }

    @Override
    public Profile getProfile(Long id) {
        return profileRepository.getById(id);
    }

}
