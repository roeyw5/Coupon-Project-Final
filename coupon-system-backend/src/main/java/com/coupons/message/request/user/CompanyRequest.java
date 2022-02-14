package com.coupons.message.request.user;

import com.coupons.model.user.Company;

import lombok.Getter;
import lombok.Setter;

/*
 * Company cast used for Controller requests
 */

@Getter
@Setter
public class CompanyRequest extends UserRequest {

	private String name;

	public Company toCompany() {
		Company company = new Company(name, email, password);
		company.setId(id);
		return company;
	}
}
