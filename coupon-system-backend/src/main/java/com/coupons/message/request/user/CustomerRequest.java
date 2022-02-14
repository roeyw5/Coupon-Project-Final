package com.coupons.message.request.user;

import com.coupons.model.user.Customer;

import lombok.Getter;
import lombok.Setter;

/*
 * Customer cast used for Controller requests
 */

@Getter
@Setter
public class CustomerRequest extends UserRequest {

	private String firstName;
	private String lastName;

	
	public Customer toCustomer() {
		Customer customer = new Customer(firstName, lastName, email, password);
		customer.setId(id);
		return customer;
	}

}
