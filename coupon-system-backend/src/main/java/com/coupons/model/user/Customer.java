package com.coupons.model.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.coupons.model.Coupon;
import com.coupons.utility.service.UserType;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

/*
 * Used for creating Customer entity in the system.
 */

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends User {

	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	
	//List of coupons that are connected to the customer(purchased by it)
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "customersVsCoupons", joinColumns = @JoinColumn(name = "customerId"), inverseJoinColumns = @JoinColumn(name = "couponId"))
	@JsonManagedReference
	private Set<Coupon> coupons;

	public Customer() {
		role = UserType.CUSTOMER;
	}
	public Customer(String firstName, String lastName, String email, String password) {
		coupons = new HashSet<>();
		role = UserType.CUSTOMER;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	@Override
	public String toString() {
		String string = "Customer(id=" + id + ", first name=" + firstName + ", last name=" + lastName + ", email="
				+ email + ", password=" + password;
		if (coupons.isEmpty())
			return string + ", coupons=None)";
		return string + ", coupons=" + coupons + ")";
	}

	
}
