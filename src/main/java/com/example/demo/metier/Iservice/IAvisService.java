package com.example.demo.metier.Iservice;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.Avis;

@Service
public interface IAvisService {
	
	public Avis addAvis(Avis avis);
	public Optional<Avis> getAvisById(long id);
	public Avis UpdateAvis(Avis avis);
	public List<Avis> getAllAvis();
	public void deleteAvis(long id);

}
