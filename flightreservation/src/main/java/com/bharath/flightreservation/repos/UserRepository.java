package com.bharath.flightreservation.repos;

import org.springframework.data.repository.CrudRepository;

import com.bharath.flightreservation.entities.User;

public interface UserRepository extends CrudRepository<User, Long> {

	/**
	 * spring creates internally 
	 * select * from user where email=" passed vale" 
	 */
	User findByEmail(String email);

}
