package com.coupons.message.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

/*
 * Used for JWT responses.
 */

@Setter
@Getter
public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private String username;
	
	private Collection<? extends GrantedAuthority> authorities;

	public JwtResponse(String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
		this.token = accessToken;
		this.username = username;
		this.authorities = authorities;
	}

}
