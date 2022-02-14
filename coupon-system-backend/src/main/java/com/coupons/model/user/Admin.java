package com.coupons.model.user;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.coupons.utility.service.UserType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * Used for creating Admin entity in the system.
 */

@Getter
@Setter
@ToString
@Entity
@Table(name = "admins")
public class Admin extends User {

	public Admin() {
		role = UserType.ADMIN;
	}
	
	public Admin(String email, String password) {
		role = UserType.ADMIN;
		this.email = email;
		this.password = password;
	}

}
