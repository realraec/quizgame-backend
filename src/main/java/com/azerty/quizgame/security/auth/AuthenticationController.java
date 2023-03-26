package com.azerty.quizgame.security.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "/api/auth")
@RequiredArgsConstructor
public class AuthenticationController
{

	private final AuthenticationService service;

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

}