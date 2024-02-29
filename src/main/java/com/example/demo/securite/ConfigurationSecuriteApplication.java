package com.example.demo.securite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.metier.service.UserServiceImp;

@Configuration
@EnableWebSecurity
public class ConfigurationSecuriteApplication {
	
	@Autowired
	private JwtFilter filter;
	
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder ;
	
	public ConfigurationSecuriteApplication(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		return httpSecurity.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(
						authorize ->
						       authorize.requestMatchers("/inscription").permitAll()
						       .requestMatchers("/activation").permitAll()
						       .requestMatchers("/connexion").permitAll()
						       .anyRequest().authenticated()
				 ).sessionManagement(httpSecuritySessionManagementConfigurer -> 
				 httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				 ).addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
				.build(); 
				
	}
	
	
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserServiceImp();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.userDetailsService());
		provider.setPasswordEncoder(this.bCryptPasswordEncoder);
		return provider;
	}

}
