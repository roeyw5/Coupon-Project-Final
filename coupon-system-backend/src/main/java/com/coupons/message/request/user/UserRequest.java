package com.coupons.message.request.user;

import lombok.Getter;
import lombok.Setter;

/*
 * User cast used for Controller requests
 */

@Setter
@Getter

public class UserRequest {

	protected int id;
	protected String email;
	protected String password;

}
