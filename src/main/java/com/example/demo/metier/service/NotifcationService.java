package com.example.demo.metier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.Validation;

@Service
public class NotifcationService {
	
	@Autowired
	JavaMailSender javaMailSender;
	
	public void envoyer(Validation validation) {
		
		SimpleMailMessage message= new SimpleMailMessage();
		message.setFrom("marwenbelakhel889@gmail.com");
		message.setSubject("Votre Code d'activation");
		message.setTo(validation.getUser().getMail());
		String text = String.format("Bonjour %s, Votre code d'activation est %s, A bientot ", validation.getUser().getNom(),validation.getCode());
		message.setText(text);
		this.javaMailSender.send(message);
	}

}
