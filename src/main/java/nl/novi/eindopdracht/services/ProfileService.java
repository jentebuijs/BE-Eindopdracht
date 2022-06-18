package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.models.Profile;
import nl.novi.eindopdracht.models.User;

import java.util.List;

public interface ProfileService {
    void profileFromUser(User user);
    List<Profile> getProfiles();
    Profile getProfile(Long id);
}
