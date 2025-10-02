package com.sintad.gestion_clientes.service.security;

import java.time.Instant;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



@Service
public class JwtService {
	
	//private final SecretKey secretKey = Jwts.SIG.HS256.key().build();
    private final SecretKey secretKey = Keys.hmacShaKeyFor(Jwts.SIG.HS256.key().build().getEncoded());
    private final JwtParser parser = Jwts.parser().verifyWith(this.secretKey).build();

	public String generateToken(String username) {
		return Jwts.builder()
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + (60*60*250)))
				.signWith(secretKey)
				.compact();
				
	}
	
	public String extractUsername(String token) {
		return parser.parseSignedClaims(token).getPayload().getSubject();
	}


	public boolean validateToken(String token, UserDetails userDetails) {
        return parser.parseSignedClaims(token).getPayload().getExpiration().after(Date.from(Instant.now()))
                && userDetails.getUsername().equals(extractUsername(token));
	}


}
