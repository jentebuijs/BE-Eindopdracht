package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.exceptions.AlreadyInUseException;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.User;
import nl.novi.eindopdracht.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ProfileService profileService;
    private final RequestService requestService;

    public UserService(UserRepository userRepository, ProfileService profileService, RequestService requestService) {
        this.userRepository = userRepository;
        this.profileService = profileService;
        this.requestService = requestService;
    }

    //METHODS//
    private User fromDtoToUser(UserInputDto userInputDto) {
        User user = new User();
        user.setUsername(userInputDto.getUsername());
        user.setEmail(userInputDto.getEmail());
        user.setPassword(userInputDto.getPassword());
        user.setIsStudent(userInputDto.getIsStudent());
        return user;
    }

    private UserOutputDto fromUserToDto(User user) {
//        Optional<User> possibleUser = userRepository.findById(user.getId());
//        if (possibleUser.isEmpty()) {
//            throw new RecordNotFoundException("Deze gebruiker is niet bekend");
//        }
        UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setId(user.getId());
        userOutputDto.setUsername(user.getUsername());
        userOutputDto.setEmail(user.getEmail());
        userOutputDto.setIsStudent(user.getIsStudent());

        return userOutputDto;
    }

    public UserOutputDto addUser(UserInputDto userInputDto) {

        Optional<User> possibleUser = userRepository.findUserByUsername(userInputDto.getUsername());
        if (possibleUser.isPresent()) {
            throw new AlreadyInUseException("Deze gebruikersnaam is al in gebruik");
        }

        possibleUser = userRepository.findUserByEmail(userInputDto.getEmail());
        if (possibleUser.isPresent()) {
            throw new AlreadyInUseException("Dit emailadres is al in gebruik");
        }

        User user = userRepository.save(fromDtoToUser(userInputDto));
        profileService.profileFromUser(user);
        return fromUserToDto(user);
    }

    public UserOutputDto getUserByUsername(String username) {
        Optional<User> possibleUser = userRepository.findUserByUsername(username);
        if (possibleUser.isEmpty()) {
            throw new RecordNotFoundException("Deze gebruikersnaam is niet bekend");
        }
        return fromUserToDto(userRepository.getUserByUsername(username));
    }

    public UserOutputDto getUserByEmail(String email) {
        Optional<User> possibleUser = userRepository.findUserByEmail(email);
        if (possibleUser.isEmpty()) {
            throw new RecordNotFoundException("Dit emailadres is niet bekend");
        }
        return fromUserToDto(userRepository.getUserByEmail(email));
    }

    public UserOutputDto updateUser(Long userId, UserInputDto userInputDto) {
        Optional<User> possibleUser = userRepository.findById(userId);
        if (possibleUser.isEmpty()) {
            throw new RecordNotFoundException("Deze gebruiker is niet bekend");
        } User updatedUser = fromDtoToUser(userInputDto);
        updatedUser.setId(userId);
        return fromUserToDto(userRepository.save(updatedUser));
    }

    public void deleteUser(Long userId) {
        Optional<User> possibleUser = userRepository.findById(userId);
        if (possibleUser.isEmpty()) {
            throw new RecordNotFoundException("Deze gebruiker is niet bekend");
        } userRepository.deleteById(userId);
    }
}
