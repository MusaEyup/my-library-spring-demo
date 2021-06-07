package com.spring.app.dao;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value = "SELECT U FROM User U WHERE U.isEnabled = TRUE")
	List<User> findAllUsers();
	
	@Query(value = "SELECT U FROM User U WHERE U.id = :id AND U.isEnabled = TRUE ")
	User findUserById(@Param("id") Long id);
	
	@Query(value = "SELECT U FROM User U WHERE U.firstName = :firstname AND U.isEnabled = TRUE")
	User findByName(@Param("firstname") String firstname);

	@Query(value = "SELECT U FROM User U WHERE U.username = :username")
	User findByUsername(@Param("username") String userName);

	@Query(value = "SELECT CASE WHEN COUNT(U) > 0 THEN TRUE ELSE FALSE END " +
						"from User U where U.email=:email")
	Boolean isEmailExist(@Param("email") String email);

	@Query(value = "SELECT CASE WHEN COUNT(U) > 0 THEN TRUE ELSE FALSE END " +
			"from User U where U.username=:username")
	Boolean isUsernameExist(@Param("username") String username);
	
	

}
