package com.example.demo.securite;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.entity.User;
import com.example.demo.dto.AuthentificationDTO;
import com.example.demo.metier.service.UserServiceImp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.websocket.Decoder;


@Service
public class JwtService {
	
	private final String Enc_key="28e65c6afd4f3e61b9689a52ba0d8eb2528a0121686378028494a76f90900687";
	
	@Autowired
	private UserServiceImp userServiceImp;
	
	public Map<String, String> generated(String mail){
		User user = (User) this.userServiceImp.loadUserByUsername(mail);
		return this.generated(user);
	}

	private Map<String, String> generated(User user) {
		
		final long currentTime = System.currentTimeMillis();
		final long exTime = currentTime + 30*60*1000;
		final Map<String,Object> clains = Map.of(
				"nom",user.getNom(),
				"mail",user.getMail(),
				Claims.EXPIRATION, new Date(exTime),
				Claims.SUBJECT,user.getMail()
				);
		
		
		
		final String jws = Jwts.builder()
		.setIssuedAt(new Date(currentTime))
		.setExpiration(new Date(exTime))
		.setSubject(user.getMail())
		.setClaims(clains)
		.signWith(key(),SignatureAlgorithm.HS256)
		.compact();
		
		return Map.of("Token",jws);
	}
	
	
	
	
	
	private Key key() {
		final byte[] decoder= Decoders.BASE64.decode(Enc_key);
		
		return Keys.hmacShaKeyFor(decoder);
	}
	
	

	public String lireMail(String token) {
		
		return this.getClaim(token,Claims::getSubject);
	}

	public boolean isTokenExpired(String token) {
		Date expiration = this.getClaim(token,Claims::getExpiration);
		return expiration.before(new Date());
	}


	private <T> T getClaim(String token, Function<Claims, T> function) {
		
		Claims claims = getAllClaims(token);
		
		return function.apply(claims);
	}

	private Claims getAllClaims(String token) {
		// TODO Auto-generated method stub
		return Jwts
				.parser()
				.setSigningKey(this.key())
				.build()
				.parseClaimsJws(token)
				.getBody()
				;
	}

}
