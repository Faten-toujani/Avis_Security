package com.example.demo.dao.entity;

import java.time.Instant;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Validation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private Instant creation;
	private Instant expiration;
	private Instant activation;
	private String code;
	
	@OneToOne(cascade = CascadeType.ALL)
	private User user;

	public Validation(Instant creation, Instant expiration, Instant activation, String code, User user) {
		super();
		this.creation = creation;
		this.expiration = expiration;
		this.activation = activation;
		this.code = code;
		this.user = user;
	}

	public Validation() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Instant getCreation() {
		return creation;
	}

	public void setCreation(Instant creation) {
		this.creation = creation;
	}

	public Instant getExpiration() {
		return expiration;
	}

	public void setExpiration(Instant expiration) {
		this.expiration = expiration;
	}

	public Instant getActivation() {
		return activation;
	}

	public void setActivation(Instant activation) {
		this.activation = activation;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
