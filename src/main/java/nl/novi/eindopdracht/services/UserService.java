package nl.novi.eindopdracht.services;

import nl.novi.eindopdracht.dtos.AuthDto;
import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.exceptions.AlreadyInUseException;
import nl.novi.eindopdracht.exceptions.RecordNotFoundException;
import nl.novi.eindopdracht.models.*;
import nl.novi.eindopdracht.repositories.AuthorityRepository;
import nl.novi.eindopdracht.repositories.FileUploadRepository;
import nl.novi.eindopdracht.repositories.ProfileRepository;
import nl.novi.eindopdracht.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final FileUploadRepository fileUploadRepository;
    private final AuthorityRepository authorityRepository;
    private final ProfileRepository profileRepository;
    private final RequestService requestService;
    private final JwtService jwtService;

    public UserService(AuthenticationManager authenticationManager, UserRepository userRepository,
                       FileUploadRepository fileUploadRepository, AuthorityRepository authorityRepository,
                       ProfileRepository profileRepository, RequestService requestService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.fileUploadRepository = fileUploadRepository;
        this.authorityRepository = authorityRepository;
        this.profileRepository = profileRepository;
        this.requestService = requestService;
        this.jwtService = jwtService;
    }

    //METHODS//
    public UserOutputDto signUp(UserInputDto userInputDto) {
        Optional<User> possibleUser = userRepository.findById(userInputDto.getUsername());
        if (possibleUser.isPresent()) {
            throw new AlreadyInUseException("Deze gebruikersnaam is al in gebruik");
        }
        possibleUser = userRepository.findUserByEmail(userInputDto.getEmail());
        if (possibleUser.isPresent()) {
            throw new AlreadyInUseException("Dit emailadres is al in gebruik");
        }

        User user = new User(
                userInputDto.getUsername(),
                userInputDto.getEmail());
        Profile profile = new Profile(
                userInputDto.getUsername(),
                userInputDto.getFirstName(),
                userInputDto.getLastName(),
                userInputDto.getDob(),
                userInputDto.getLevel(),
                userInputDto.getContactIntensity(),
                userInputDto.getAboutMe());

        user.setProfile(profile);
        Set<String> strAuthorities = userInputDto.getAuthorities();
        Set<Authority> authorities = new HashSet<>();
        if (strAuthorities != null) {
            strAuthorities.forEach(authority -> {switch (authority) {
                    case "ADMIN" -> {
                        Authority adminRole = authorityRepository.findByName(EAuthority.ROLE_ADMIN)
                                .orElseThrow(() ->
                                        new RuntimeException("Error: Role is not found."));
                        authorities.add(adminRole);
                    }
                    case "BUDDY" -> {
                        Authority buddyRole = authorityRepository.findByName(EAuthority.ROLE_BUDDY)
                                .orElseThrow(() ->
                                        new RuntimeException("Error: Role is not found."));
                        authorities.add(buddyRole);
                    }
                    case "STUDENT" -> {
                        Authority studentRole = authorityRepository.findByName(EAuthority.ROLE_STUDENT)
                                .orElseThrow(() ->
                                        new RuntimeException("Error: Role is not found."));
                        authorities.add(studentRole);
                    }
                }
            });
        }
        user.setAuthorities(authorities);
        user.setEnabled(true);
        return fromUserToDto(userRepository.save(user));
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

    public UserOutputDto updateUser(String username, UserInputDto userInputDto) {
        Optional<User> possibleUser = userRepository.findById(username);
        if (possibleUser.isEmpty()) {
            throw new RecordNotFoundException("Deze gebruiker is niet bekend");
        }
        User updatedUser = possibleUser.get();
        updatedUser.setEmail(userInputDto.getEmail());
//        updatedUser.setEnabled(userInputDto.isEnabled());
        return fromUserToDto(userRepository.save(updatedUser));
    }

    public void deleteUser(String username) {
        boolean userExists = userRepository.existsById(username);
        if (userExists == false) {
            throw new RecordNotFoundException("Deze gebruiker is niet bekend");
        }
        userRepository.deleteById(username);
    }

    public void assignPhotoToStudent(String fileName, String username) {
        Optional<User> optionalUser = userRepository.findById(username);
        Optional<FileUploadResponse> fileUploadResponse = fileUploadRepository.findByFileName(fileName);
        if (optionalUser.isPresent() && fileUploadResponse.isPresent()) {
            FileUploadResponse photo = fileUploadResponse.get();
            User user = optionalUser.get();
            user.setFileUploadResponse(photo);
            userRepository.save(user);
        }
    }

    private User fromDtoToUser(UserInputDto userInputDto) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setUsername(userInputDto.getUsername());
        user.setEmail(userInputDto.getEmail());
        String encryptedPassword = passwordEncoder.encode(userInputDto.getPassword());
        user.setPassword(encryptedPassword);
        user.setEnabled(true);
        return user;
    }

    private UserOutputDto fromUserToDto(User user) {
        UserOutputDto userOutputDto = new UserOutputDto();
        userOutputDto.setUsername(user.getUsername());
        userOutputDto.setEmail(user.getEmail());
        userOutputDto.setEnabled(user.getEnabled());
        return userOutputDto;
    }
}
