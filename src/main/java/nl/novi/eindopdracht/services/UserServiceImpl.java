package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //METHODS//
    @Override
    public User toUser(UserInputDto userInputDto) {
        User user = new User();
        user.setUsername(userInputDto.getUsername());
        user.setEmail(userInputDto.getEmail());
        user.setPassword(userInputDto.getPassword());
        return user;
    }

    @Override
    public UserOutputDto fromUser(User user) {
        Optional<User> fromUser = userRepository.findById(user.getId());
        if (fromUser.isEmpty()) {
            System.out.println("Mislukt");
        }
        UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setId(user.getId());
        userOutputDto.setUsername(user.getUsername());
        userOutputDto.setEmail(user.getEmail());
        return userOutputDto;
    }

    @Override
    public User addUser(UserInputDto userInputDto) {
        //check whether email or username are available//
        User user = new User();
        user.setUsername(userInputDto.getUsername());
        user.setEmail(userInputDto.getEmail());
        user.setPassword(userInputDto.getPassword());
        return userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    public User getUserByEmail(String email) {
        System.out.println(email);
        User result = userRepository.getUserByEmail(email);
        System.out.println(result);
        return result;
    }

    @Override
    public List<User> getAllStudents() {
        return userRepository.getAllUsersByIsStudentIsTrue();
    }

    @Override
    public List<User> getAllBuddies() {
        return userRepository.getAllUsersByIsStudentIsFalse();
    }


}
