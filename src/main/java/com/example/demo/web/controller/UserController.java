package com.example.demo.web.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.entity.User;
import com.example.demo.dto.AuthentificationDTO;
import com.example.demo.metier.Iservice.IUserService;
import com.example.demo.metier.Iservice.IValidationService;
import com.example.demo.securite.JwtService;


@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	IValidationService validationService;
	
	@Autowired
	JwtService jwtService;
	
	@PostMapping(path ="inscription")
	public String  inscription(@ RequestBody User user) {
		this.userService.inscription(user);
		return "Terminer Inscription";
		
	}
	
	
	
	@PostMapping(path ="activation")
	public String  activation(@ RequestBody Map<String,String> activation  ) {
		this.userService.activation(activation);
		return "Activation Terminer";
		
	}
	@PostMapping("/connexion")
	public Map<String,String> connexion(@RequestBody AuthentificationDTO dto){
		final Authentication authentication= authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.mail(), dto.password())
				);
		if(authentication.isAuthenticated()) {
			return this.jwtService.generated(dto.mail());
		}
		

		return null;
	}
	
	
}
