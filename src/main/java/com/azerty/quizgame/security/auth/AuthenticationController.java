package com.azerty.quizgame.security.auth;

import com.azerty.quizgame.model.dto.PersonDTO;
import com.azerty.quizgame.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final PersonService personService;


    @PostMapping(path = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception e) {
            e.printStackTrace();
            return (ResponseEntity<AuthenticationResponse>) ResponseEntity.internalServerError();
        }
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PutMapping(path = "/password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordRequest request) {
        try {
            PersonDTO personDto = personService.getPersonById(request.getId());
            return ResponseEntity.ok(authenticationService.changePassword(request, personDto));
        } catch (Exception e) {
            e.printStackTrace();
            return (ResponseEntity<String>) ResponseEntity.badRequest();
        }
    }

}