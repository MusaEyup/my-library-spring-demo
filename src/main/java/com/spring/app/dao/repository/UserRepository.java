package com.spring.app.dao.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.app.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	
	
	
	@Query(value = "select u from User u ")
	public List<User> findAllUsers();
	
	@Query(value = "select max(u.id) from User u")
	public Long findMaxId();
	
	@Query(value = "select u From User u where u.id = :id")
	public User findUserById(@Param("id") Long id);
	
	@Query(value = "SELECT * FROM users u WHERE u._name = :keyword", nativeQuery=true)
	public User findByName(@Param ("keyword") String Name);
	
	@Query(value = "select * from users u where u.user_name = :keyword", nativeQuery = true)
	public User findByUserName(@Param ("keyword") String userName);
	
	
	

}
