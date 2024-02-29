package com.example.demo.dao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "role")
public class Role {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@Enumerated(EnumType.STRING)
	private TypedeRole libelle;

	public Role(TypedeRole libelle) {
		super();
		this.libelle = libelle;
	}

	public Role() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TypedeRole getLibelle() {
		return libelle;
	}

	public void setLibelle(TypedeRole libelle) {
		this.libelle = libelle;
	}
	
	
	
}
