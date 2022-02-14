package com.coupons.tokensManager.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.coupons.model.user.User;

/**
 *JWT class for implementing and managing the UserDetails interface
 **/

public class UserPrinciple implements UserDetails {

	private static final long serialVersionUID = -8729678558012322165L;

	private int id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserPrinciple(int id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	/**
	 * Returns a new instance of a UserPrinciple based on the user
	 */
	public static UserPrinciple build(User user) {
		List<GrantedAuthority> newAuthorities = new ArrayList<>(
				Arrays.asList(new SimpleGrantedAuthority(user.getRole().name())));
		return new UserPrinciple(user.getId(), user.getEmail(), user.getPassword(), newAuthorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	
	public int getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
