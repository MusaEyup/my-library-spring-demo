package com.spring.app.controller;

import com.spring.app.dto.UserDto;
import com.spring.app.results.DataResult;
import com.spring.app.business.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
	public DataResult<List<UserDto>> getAllUsers(){
		return userService.getAllUsers();
	}
	

	@GetMapping( "/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public DataResult<UserDto> getUserById(@PathVariable("id") Long id){
		return userService.getUserById(id);
	}

	@GetMapping(value = "/name={name}")
	@PreAuthorize("hasRole('ADMIN')")
	public DataResult<UserDto> getUserByName(@PathVariable("name") String name){
		return userService.getUserByName(name);
	}

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public DataResult<UserDto> createNewAccount(@RequestBody UserDto user) {

		return  userService.register(user);
	}
}
