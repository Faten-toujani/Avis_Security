package com.example.demo.metier.service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.Role;
import com.example.demo.dao.entity.TypedeRole;
import com.example.demo.dao.entity.User;
import com.example.demo.dao.entity.Validation;
import com.example.demo.dao.repository.UserRepository;
import com.example.demo.dao.repository.ValidationRepisitory;
import com.example.demo.metier.Iservice.IUserService;
import com.example.demo.metier.Iservice.IValidationService;


@Service
public class UserServiceImp implements IUserService,UserDetailsService{
	
	 
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private IValidationService validationServiceImp;
	@Autowired
	ValidationRepisitory validationRepisitory;
	
	public void inscription(User user) {
		String mdp = this.bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(mdp);
		Role role = new Role();
		role.setLibelle(TypedeRole.USER);
		user.setRole(role);
		
		Optional<User> use = this.userRepository.findUserByMail(user.getMail());
		if(use.isPresent()) {
			throw new RuntimeException("Mail Deja Existe ");
		}
		this.userRepository.save(user);
		this.validationServiceImp.enregistration(user);
	}

	@Override
	public void activation(Map<String, String> activation) {
		Validation validation=this.validationServiceImp.lireEnFonctionCode(activation.get("code"));
		if(Instant.now().isAfter(validation.getExpiration())) {
			throw new RuntimeException("votre message est expiré");
		}
		validation.getUser().setActif(true);
		this.userRepository.save(validation.getUser());
		validation.setActivation(Instant.now());
		this.validationRepisitory.save(validation);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return this.userRepository.findUserByMail(username).orElseThrow(()->new RuntimeException("User Not Existe"));
	}
	

}
