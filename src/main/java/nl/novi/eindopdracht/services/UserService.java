package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.models.User;

import java.util.List;

public interface UserService {
    User toUser(UserInputDto userInputDto);

    UserOutputDto fromUser(User user);

    User addUser(UserInputDto userInputDto);

    User getUserByUsername(String username);

    User getUserByEmail(String email);

    List<User> getAllStudents();

    List<User> getAllBuddies();



//    List<User> getBuddies();
//
//    List<User> getStudents();

}
