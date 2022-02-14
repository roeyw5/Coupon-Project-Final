package com.coupons.tokensManager.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.coupons.tokensManager.services.UserPrinciple;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 *JWT component for the security system to create tokens
 **/

@Component
public class JwtProvider {

	@Value("${com.jwt.secret}")
	private String jwtSecret;
	
	//Amount of time before a token expires, in minutes
	private final int JWT_EXPIRATION_MINUTES = 30;


	/**
	 * Generates a JWT token for authentication
	 */
	public String generateJwtToken(Authentication authentication) {
		UserPrinciple userPrincipal = (UserPrinciple) authentication.getPrincipal();
		System.out.println("New token generated");
		return Jwts.builder().setSubject((userPrincipal.getUsername())).setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + JWT_EXPIRATION_MINUTES * 1000 * 60))
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
		
	}

	/**
	 * Returns true if the token is valid.
	 */
	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid JWT signature -> Message: " + e);
		} catch (MalformedJwtException e) {
			System.out.println("Invalid JWT token -> Message: " + e);
		} catch (ExpiredJwtException e) {
			System.out.println("Expired token -> Message: " + e);
		} catch (UnsupportedJwtException e) {
			System.out.println("Unsupported JWT token -> Message: " + e);
		} catch (IllegalArgumentException e) {
			System.out.println("JWT claims string is empty -> Message: " + e);
		}
		return false;
	}

	 /**
	 * Returns a Username from the token
	 */
	public String getUsernameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

}
