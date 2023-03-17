package com.azerty.quizgame.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.azerty.quizgame.dao.PersonDAO;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig
{
	private final PersonDAO personDao;

	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsService()
		{
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
			{
				return personDao.findAnyPersonByUsername(username)
						.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));
			}
		};
	}

	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider authProviderDAO = new DaoAuthenticationProvider();
		authProviderDAO.setUserDetailsService(userDetailsService());
		authProviderDAO.setPasswordEncoder(passwordEncoder());
		return authProviderDAO;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception
	{
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
}