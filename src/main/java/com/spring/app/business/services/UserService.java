package com.spring.app.business.services;


import java.util.List;

import com.spring.app.dto.UserDto;
import com.spring.app.entities.User;
import com.spring.app.results.DataResult;

public interface UserService {

	User loadUser(Long userId);
	DataResult<UserDto> getUserById(Long Id);
	DataResult<UserDto> getUserByName(String name);
	DataResult<UserDto> register(UserDto userDto);
	DataResult<List<UserDto>> getAllUsers();

	DataResult<User> getUserByUsername(String userName);


	
}
