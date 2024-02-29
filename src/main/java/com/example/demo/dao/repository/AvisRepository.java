package com.example.demo.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.entity.Avis;


@Repository
public interface AvisRepository extends JpaRepository<Avis, Long>{

}
