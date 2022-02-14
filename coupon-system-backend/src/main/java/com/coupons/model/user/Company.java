package com.coupons.model.user;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.coupons.model.Coupon;
import com.coupons.utility.service.UserType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

/*
 * Used for creating Company entity in the system.
 */

@Getter
@Setter
@Entity
@Table(name = "companies")
public class Company extends User {

	@Column(nullable = false, unique = true)
	private String name;
	
	//The coupons that are connected to the company("belongs" to it)
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Coupon> coupons;

	public Company(String name, String email, String password) {
		coupons = new ArrayList<Coupon>();
		role = UserType.COMPANY;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		String string = "Company(id=" + id + ", name=" + name + ", email=" + email + ", password=" + password;
		if (coupons.isEmpty())
			return string + ", coupons=None)";
		return string + ", coupons=" + coupons + ")";
	}
	
	//Helper method - Set a role of user
		public Company() {
			role = UserType.COMPANY;
		}

}
