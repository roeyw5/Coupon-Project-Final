package com.coupons.service.ifc;

/*
 * Interface with the methods for logging into the system.
 */

public interface ClientLogin {

	/*
	 * Returns true if it finds a client with these credentials in the system
	 */
	public boolean login(String email, String password);

}
