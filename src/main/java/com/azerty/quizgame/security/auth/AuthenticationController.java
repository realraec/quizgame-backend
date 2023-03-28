package com.azerty.quizgame.security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.azerty.quizgame.model.dto.PersonDTO;
import com.azerty.quizgame.service.PersonService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthenticationController
{

	private final AuthenticationService service;
	private final PersonService personService;

	@PostMapping(path = "/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request)
	{
		try
		{
			return ResponseEntity.ok(service.register(request));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return (ResponseEntity<AuthenticationResponse>) ResponseEntity.internalServerError();
	}

	@PostMapping(path = "/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
	{
		return ResponseEntity.ok(service.authenticate(request));
	}

	@PutMapping(path = "/password")
	public ResponseEntity<String> passwordChanged(@RequestBody PasswordRequest request)
	{
		try
		{
			PersonDTO personDto = personService.getPersonById(request.getId());
			return ResponseEntity.ok(service.changePassword(request, personDto));
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
		return (ResponseEntity<String>) ResponseEntity.badRequest();
	}
}