package com.coupons.tokensManager;

import org.springframework.security.core.context.SecurityContextHolder;

import com.coupons.tokensManager.services.UserPrinciple;

/**
 *Interface containing methods related to JWT tokens
 **/

public interface TokenUtil {

	/**
	 * Returns The id of the logged in User.
	 */
	public default int getUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		int id = 0;
		if (principal instanceof UserPrinciple)
			id = ((UserPrinciple) principal).getId();
		return id;
	}

}
