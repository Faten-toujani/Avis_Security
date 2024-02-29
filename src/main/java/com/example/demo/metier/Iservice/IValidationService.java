package com.example.demo.metier.Iservice;

import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.User;
import com.example.demo.dao.entity.Validation;

@Service
public interface IValidationService {
	
	public void enregistration(User user);
	public Validation lireEnFonctionCode(String code);

}
