package com.example.demo.metier.Iservice;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.User;

@Service
public abstract interface IUserService {
	
	public  void inscription(User user) ;

	public void activation(Map<String, String> activation); 

}
