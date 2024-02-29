package com.example.demo.securite;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.dao.entity.User;
import com.example.demo.metier.service.UserServiceImp;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtFilter extends OncePerRequestFilter{
	
	@Autowired
	private UserServiceImp userServiceImp;
	
	@Autowired
	private JwtService jwtService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = null;
		String mail = null;
		boolean experd=true;
		
		String aout = request.getHeader("Authorization");
		if(aout != null && aout.startsWith("Bearer ")) {
			token = aout.substring(7);
			experd = jwtService.isTokenExpired(token);
			mail=this.jwtService.lireMail(token);
		}
		
		if(!experd&& mail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			User userDetails= (User) userServiceImp.loadUserByUsername(mail);
			UsernamePasswordAuthenticationToken token2 = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(token2);
		}
		
		filterChain.doFilter(request, response);
		
	}
	
	
	

}
