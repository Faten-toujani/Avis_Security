package com.example.demo.metier.service;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.entity.Validation;
import com.example.demo.dao.repository.ValidationRepisitory;
import com.example.demo.metier.Iservice.IValidationService;

@Service
public class ValidationServiceImp implements IValidationService{
	
	@Autowired
	private ValidationRepisitory validationRepisitory;
	
	@Autowired
	NotifcationService notifcationService;
	
	@Override
	public void enregistration(User user) {
		
		Validation validation=new Validation();
		validation.setUser(user);
		Instant creation =Instant.now();
		validation.setCreation(creation);
		Instant experation = creation.plus(Duration.ofMinutes(10));
		validation.setExpiration(experation);
		Random random=new Random();
		int codeR = random.nextInt(999999);
		String code = String.format("%06d",codeR);
		validation.setCode(code);
		this.validationRepisitory.save(validation);
		this.notifcationService.envoyer(validation);
		 
	}
	
	public Validation lireEnFonctionCode(String code) {
		return this.validationRepisitory.findByCode(code).orElseThrow(()-> new RuntimeException("Votre Code est Invalide"));
		
	}
	
}
 