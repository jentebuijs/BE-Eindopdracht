package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.repositories.ProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileServiceImpl(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void profileFromUser(User user) {
        Profile profile = new Profile();
        profile.setId(user.getId());
        profile.setIsStudent(user.getIsStudent());
        profileRepository.save(profile);
    }

}
