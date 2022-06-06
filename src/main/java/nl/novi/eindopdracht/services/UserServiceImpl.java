package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //METHODS//
    @Override
    public UserInputDto toUser(UserInputDto userInputDto) {
        return userInputDto;
    }

    @Override
    public User fromUser(User user) {
        return user;
    }

    @Override
    public User addUser(User user) {
        userRepository.save(user);
        return user;
    }
}
