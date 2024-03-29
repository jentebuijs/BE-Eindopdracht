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
        String authority = userInputDto.getAuthority().toString();
        Set<Authority> authorities = new HashSet<>();
        switch (authority) {
            case "Buddy" -> {
                Authority authority1 = authorityRepository.getByName(EAuthority.ROLE_BUDDY);
                authorities.add(authority1);
                user.getProfile().setRole(authority1.getName());
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
//
//    public UserOutputDto getUser(String username) {
//        Optional<User> optionalUser = userRepository.findById(username);
//        if (optionalUser.isEmpty()) {
//            throw new RecordNotFoundException("Deze gebruiker is niet bekend");
//        }
//        return fromUserToDto(optionalUser.get());
//    }

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

    public String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
