package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.AuthDto;
import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.exceptions.AlreadyInUseException;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.*;
import nl.novi.eindopdracht.repositories.AuthorityRepository;
import nl.novi.eindopdracht.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final RequestService requestService;
    private final JwtService jwtService;

    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository,
                       AuthorityRepository authorityRepository,
                       RequestService requestService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.requestService = requestService;
        this.jwtService = jwtService;
    }

    //METHODS//
    public User signUp(UserInputDto userInputDto) {
        Optional<User> possibleUser = userRepository.findById(userInputDto.getUsername());
        if (possibleUser.isPresent()) {
            throw new AlreadyInUseException("Deze gebruikersnaam is al in gebruik");
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userInputDto.setPassword(passwordEncoder.encode(userInputDto.getPassword()));
        User user = new User(userInputDto);
        String authority = userInputDto.getAuthority();
        Set<Authority> authorities = new HashSet<>();
        switch (authority) {
            case "Buddy" -> {
                authorities.add(authorityRepository.getByName(EAuthority.ROLE_BUDDY));
            }
            case "Student" -> {
                authorities.add(authorityRepository.getByName(EAuthority.ROLE_STUDENT));
            }
        }
        user.setAuthorities(authorities);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public String signIn(AuthDto authDto) {
        UsernamePasswordAuthenticationToken upat =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(upat);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtService.generateToken(userDetails);
    }

    public UserOutputDto getUser(String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new RecordNotFoundException("Deze gebruiker is niet bekend");
        }
        return fromUserToDto(optionalUser.get());
    }

    public List<Profile> filterByAuthorities() {
        List<String> strings = new ArrayList<>();
        SecurityContextHolder.getContext().getAuthentication().getAuthorities().forEach((authority) -> {
            strings.add(authority.toString());
        });
        List<Profile> profileList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        strings.forEach(authority -> {
            switch (authority) {
                case "ROLE_ADMIN" -> {
                    userList.forEach(user -> profileList.add(user.getProfile()));
                }
                case "ROLE_BUDDY" -> {
                    userList.stream().filter(user -> user.getAuthorities().toString().equals("ROLE_STUDENT"))
                            .forEach(user -> profileList.add(user.getProfile()));
                }
                case "ROLE_STUDENT" -> {
                    userList.stream().filter(user -> user.getAuthorities().contains("ROLE_BUDDY")).forEach(user -> profileList.add(user.getProfile()));
                }
            }
        });
        return profileList;
    }


    public UserOutputDto updateUser(String username, UserInputDto userInputDto) {
        Optional<User> possibleUser = userRepository.findById(username);
        if (possibleUser.isEmpty()) {
            throw new RecordNotFoundException("Deze gebruiker is niet bekend");
        }
        User updatedUser = possibleUser.get();
        updatedUser.setEnabled(userInputDto.getEnabled());
        return fromUserToDto(userRepository.save(updatedUser));
    }

    public void deleteUser(String username) {
        boolean userExists = userRepository.existsById(username);
        if (userExists == false) {
            throw new RecordNotFoundException("Deze gebruiker is niet bekend");
        }
        userRepository.deleteById(username);
    }

    private UserOutputDto fromUserToDto(User user) {
        UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setUsername(user.getUsername());
        userOutputDto.setEnabled(user.getEnabled());
        return userOutputDto;
    }
}
