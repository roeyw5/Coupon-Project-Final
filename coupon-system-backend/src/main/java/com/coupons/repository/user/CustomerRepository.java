package com.coupons.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coupons.model.user.Customer;

/*
 * Repository for the Customer actions in the system.
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	public Optional<Customer> findByIdNotAndEmailIgnoreCase(int id, String email);
	public Optional<Customer> findByEmailIgnoreCaseAndPassword(String email, String password);
	public Optional<Customer> findByEmailIgnoreCase(String email);

}
