package nl.novi.eindopdracht.controllers;

import nl.novi.eindopdracht.dtos.AuthDto;
import nl.novi.eindopdracht.dtos.UserInputDto;
import nl.novi.eindopdracht.dtos.UserOutputDto;
import nl.novi.eindopdracht.services.UserService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserOutputDto> addUser(@RequestBody UserInputDto userInputDto){
        UserOutputDto userOutputDto = userService.addUser(userInputDto);
        URI location = URI.create(userOutputDto.getUsername());
        return ResponseEntity.created(location).body(userOutputDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> signIn(@RequestBody AuthDto authDto) {
        UsernamePasswordAuthenticationToken upat =
                new UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(upat);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = userService.generateToken(userDetails);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body(token);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserOutputDto> updateUser(@PathVariable Long userId, @RequestBody UserInputDto userInputDto) {
        UserOutputDto userOutputDto = userService.updateUser(userId, userInputDto);
        return ResponseEntity.ok().body(userOutputDto);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().body("Gebruiker succesvol verwijderd");
    }
}
