package com.coupons.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coupons.model.user.Company;

/*
 * Repository for the Company actions in the system.
 */

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	public Optional<Company> findByIdNotAndNameIgnoreCase(int id, String name);
	public Optional<Company> findByIdNotAndEmailIgnoreCase(int id, String email);
	public Optional<Company> findByEmailIgnoreCaseAndPassword(String email, String password);
	public Optional<Company> findByEmailIgnoreCase(String email);

}
