package com.coupons.tokensManager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.coupons.repository.user.UserRepository;

/**
 *JWT service for managing the user details.
 **/

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return UserPrinciple.build(userRepository.findByEmailIgnoreCase(username).orElseThrow(
				() -> new UsernameNotFoundException("User with the email \"" + username + "\" was not found.")));
	}

}
