package com.coupons.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coupons.model.user.Admin;

/*
 * Repository for the Admin actions in the system.
 */

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	public Optional<Admin> findByEmailIgnoreCaseAndPassword(String email, String password);
	public Optional<Admin> findByIdNotAndEmailIgnoreCase(int id, String email);
	public Optional<Admin> findByEmailIgnoreCase(String email);

}
