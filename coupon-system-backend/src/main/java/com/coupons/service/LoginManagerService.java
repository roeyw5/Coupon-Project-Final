package com.coupons.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.coupons.exception.loginException.InvalidLoginException;
import com.coupons.exception.loginException.MismatchLoginException;
import com.coupons.exception.loginException.NullLoginException;
import com.coupons.message.response.JwtResponse;
import com.coupons.service.ifc.LoginManagerServiceIfc;
import com.coupons.tokensManager.jwt.JwtProvider;
import com.coupons.tokensManager.services.UserPrinciple;
import com.coupons.utility.service.UserType;

/*
 * Manages the login system, including the JWT tokes.
 * Login is based on UserType, email and password.
 */

@Service
public class LoginManagerService implements LoginManagerServiceIfc {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private CustomerService customerService;

	@Override
	public ResponseEntity<?> login(String email, String password, UserType userType)
			throws InvalidLoginException, NullLoginException, MismatchLoginException {
		if (email == null || password == null || userType == null)
			throw new NullLoginException();

		Authentication authentication = null;
		try {
			authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (Exception e) {
			if (companyService.companyExists(email) || customerService.customerExists(email)) {
				throw new InvalidLoginException(email, true, userType);
			}
			throw new InvalidLoginException(email, false, userType);
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

		Collection<? extends GrantedAuthority> authorities = null;
		switch (userType) {
		case ADMIN:
			if (userPrinciple.getAuthorities().contains(new SimpleGrantedAuthority(UserType.ADMIN.name()))) {
				authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(UserType.ADMIN.name())));
			}
			break;
		case COMPANY:
			if (userPrinciple.getAuthorities().contains(new SimpleGrantedAuthority(UserType.COMPANY.name()))) {
				authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(UserType.COMPANY.name())));
			}
			break;
		case CUSTOMER:
			if (userPrinciple.getAuthorities().contains(new SimpleGrantedAuthority(UserType.CUSTOMER.name()))) {
				authorities = new ArrayList<>(Arrays.asList(new SimpleGrantedAuthority(UserType.CUSTOMER.name())));
			}
			break;
		default:
			break;
		}
		if (authorities == null) {
			throw new MismatchLoginException();
		}
		return ResponseEntity.ok(new JwtResponse(jwt, userPrinciple.getUsername(), authorities));
	}

}
