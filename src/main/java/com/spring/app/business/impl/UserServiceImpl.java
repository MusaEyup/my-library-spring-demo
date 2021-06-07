package com.spring.app.business.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.google.common.collect.Sets;
import com.spring.app.business.services.UserService;
import com.spring.app.dto.UserDto;
import com.spring.app.results.DataResult;
import com.spring.app.results.ErrorDataResult;
import com.spring.app.results.SuccessDataResult;
import com.spring.app.business.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.spring.app.dao.UserRepository;
import com.spring.app.entities.User;

@Service("userService")
public class UserServiceImpl implements UserService {


	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleService roleService;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleService = roleService;
	}


	@Override
	public User loadUser(Long userId) {
		return userRepository.findUserById(userId);
	}

	@Override
	public DataResult<UserDto> getUserById(Long Id) {

		User user = userRepository.findUserById(Id);
		if (user != null) {

			return new SuccessDataResult<>(getUserDto(user));
		}
		return new ErrorDataResult<>("User not found");
	}



	@Override
	public DataResult<UserDto> getUserByName(String name) {
		User user = userRepository.findByName(name);
		if (user != null) {
			return new SuccessDataResult<>(getUserDto(user));
		}
		return new ErrorDataResult<>("User not found");
	}

	@Override
	@Transactional
	public DataResult<UserDto> register(UserDto userDto) {

		boolean usernameExist = userRepository.isUsernameExist(userDto.getUsername());

		if(usernameExist)
			return new ErrorDataResult<>("username is already in use please choose another");

		boolean emailExist = userRepository.isEmailExist(userDto.getEmail());

		if(emailExist)
			return new ErrorDataResult<>("email is already in use please choose another");

		User user = getUser(userDto);

		user = userRepository.save(user);

		if(user.getId() != null){
			return new SuccessDataResult<>(getUserDto(user),"Registration completed successfully");
		}
		return new ErrorDataResult<>("Something went wrong");
	}

	@Override
	public DataResult<List<UserDto>>  getAllUsers() {

		List<User> users = userRepository.findAllUsers();
		if(users != null){

			List<UserDto> userDtoList = users.stream()
					.map(this::getUserDto)
					.collect(Collectors.toList());

			return new  SuccessDataResult<>(userDtoList, "All Users list");
		}
		return new ErrorDataResult<>("no users found");
	}

	@Override
	public DataResult<User>  getUserByUsername(String username) {
		User user = userRepository.findByUsername(username);

		if(user != null){
			return new  SuccessDataResult<>(user,"1 Record found");
		}
		return new ErrorDataResult<>("no users found");
	}


	private User getUser(UserDto userDto){

		return new User(
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail(),
				userDto.getUsername(),
				passwordEncoder.encode(userDto.getPassword()) ,
				Sets.newHashSet(roleService.getRoleByName("USER").getData())
		);
	}

	private UserDto getUserDto(User user){

		return new UserDto(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getUsername(),
				user.getPassword(),
				user.getEmail(),
				null,
				user.isEnabled()
		);
	}
}

		
		


