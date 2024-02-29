package com.example.demo.metier.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.Avis;
import com.example.demo.dao.repository.AvisRepository;
import com.example.demo.metier.Iservice.IAvisService;

@Service
public class AvisServiceImp implements IAvisService{
	
	
	@Autowired
	private AvisRepository avisRepository;
	
	
	
	@Override
	public Avis addAvis(Avis avis) {
		// TODO Auto-generated method stub
		return this.avisRepository.save(avis);
	}



	@Override
	public Optional<Avis> getAvisById(long id) {
		Optional<Avis> avis = this.avisRepository.findById(id);
		
		return avis;
	}



	@Override
	public Avis UpdateAvis(Avis avis) {
		
		return this.avisRepository.save(avis);
	}



	@Override
	public List<Avis> getAllAvis() {
		// TODO Auto-generated method stub
		return this.avisRepository.findAll();
	}



	@Override
	public void deleteAvis(long id) {
		this.avisRepository.deleteById(id);
		
	}

}
