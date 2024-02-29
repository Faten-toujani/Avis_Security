package com.example.demo.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dao.entity.Validation;

public interface ValidationRepisitory extends JpaRepository<Validation, Long>{
	Optional<Validation> findByCode(String code);
}
