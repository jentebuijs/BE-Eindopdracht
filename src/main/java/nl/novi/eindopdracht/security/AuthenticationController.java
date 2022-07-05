package nl.novi.eindopdracht.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTService jwtService;

    @PostMapping("/login")
    public ResponseEntity<Object> logIn(@RequestBody AuthenticationDto authenticationDto) {
        UsernamePasswordAuthenticationToken upat =
                new UsernamePasswordAuthenticationToken(authenticationDto.getUsername(), authenticationDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(upat);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .body("Token generated");
    }
}
