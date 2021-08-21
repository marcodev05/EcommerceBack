package com.tsk.ecommerce.config.jwt;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	
	@Value("$(jwt.secret)")
	private String jwtSecret;
	
	
	/********************************
	 *  génerer le token par username
	 */
	
	public String generateToken(String login) {
		
		Date d = Date.from(LocalDate.now().plusMonths(12).atStartOfDay(ZoneId.systemDefault()).toInstant());
		
		return Jwts.builder()
					.setSubject(login)
					.setExpiration(d)
					.signWith(SignatureAlgorithm.HS512 , jwtSecret)
					.compact();
	}
	
	
	/*********************************
	 * verifier si le token est valide
	 */
	
	public boolean validateToken(String token) throws Exception {

		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;

		} catch (Exception e) {
			throw new Exception("token invalid");
		}	
	}
	
	
	/************************
	 * get username par token
	 */
	
	public String getLoginFromToken(String token) {

		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

}
