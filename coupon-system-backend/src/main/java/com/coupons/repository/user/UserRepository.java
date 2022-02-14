package com.coupons.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coupons.model.user.User;

/*
 * Repository for the managing the Users in the system.
 */

public interface UserRepository extends JpaRepository<User, Integer> {

	public Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);
	public Optional<User> findByIdNotAndEmailIgnoreCase(int id, String email);
	public Optional<User> findByEmailIgnoreCase(String email);

}
