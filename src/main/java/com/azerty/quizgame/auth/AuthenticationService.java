package com.azerty.quizgame.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.azerty.quizgame.config.JwtService;
import com.azerty.quizgame.dao.PersonDAO;
import com.azerty.quizgame.model.entity.Person;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService
{
	// private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;
	private final PersonDAO personDao;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthenticationResponse register(RegisterRequest request) throws Exception
	{
		// TODO créer un username
		Person person = new Person();
		person.setUsername(request.getUsername());
		person.setFirstname(request.getFirstname());
		person.setLastname(request.getLastname());
		person.setPassword(passwordEncoder.encode(request.getPassword()));
		person.setEmail(request.getEmail());
		person.setRole(request.getRole());

		personDao.save(person);

		String jwtToken = jwtService.generateToken(person);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request)
	{
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		Person user = personDao.findAnyPersonByUsername(request.getUsername()).orElseThrow();
		String jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder().token(jwtToken).build();
	}

}