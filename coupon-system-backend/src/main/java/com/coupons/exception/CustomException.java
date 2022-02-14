package com.coupons.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class CustomException extends Exception {

	private static final long serialVersionUID = 6196041472980746758L;
	
	 protected String errorCode;

}
