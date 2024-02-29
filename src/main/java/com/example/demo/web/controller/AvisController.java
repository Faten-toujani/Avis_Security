package com.example.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.entity.Avis;
import com.example.demo.dao.entity.User;
import com.example.demo.metier.Iservice.IAvisService;
import com.example.demo.web.model.AvisModel;

@RestController
@RequestMapping("avis")
public class AvisController {
	
	@Autowired
	private IAvisService avisService;
	
	
	@PostMapping("/add")
	public ResponseEntity<Object> addAvis(@RequestBody AvisModel model){
		//User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		return new ResponseEntity<>(this.avisService.addAvis
				(new Avis(model.getMessage(),model.getStatu()))
				,HttpStatus.CREATED);
	}
	
	@GetMapping("/allAvis")
	public ResponseEntity<Object> getAll(){
		return new ResponseEntity<>(this.avisService.getAllAvis(),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable long id){
		this.avisService.deleteAvis(id);
		return new ResponseEntity<>("Supprimer",HttpStatus.OK);
	}

}
