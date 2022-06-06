package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.models.User;

public interface UserService {
    UserInputDto toUser(UserInputDto userInputDto);

    User fromUser(User user);

    void addUser(User user);

}
