package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.models.User;
import java.util.List;

public interface UserService {

    User fromDtoToUser(UserInputDto userInputDto);

    UserOutputDto fromUserToDto(User user);

    UserOutputDto addUser(UserInputDto userInputDto);

    UserOutputDto getUserByUsername(String username);

    UserOutputDto getUserByEmail(String email);

    List<User> getAllStudents();

    List<User> getAllBuddies();

    void deleteUser(Long userId);

}
