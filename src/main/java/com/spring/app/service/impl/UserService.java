package com.spring.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.app.dao.repository.UserRepository;
import com.spring.app.entity.User;
import com.spring.app.model.UserModel;
import com.spring.app.service.IUserService;

@Service("userService")
public class UserService implements IUserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public User getByName(String name) {
		return userRepository.findByName(name);
	}

	@Override
	public User getUserById(Long Id) {
		return userRepository.findUserById(Id);
	}

	@Override
	@Transactional
	public User createNewAccount(UserModel user) {
		
		User existUser = userRepository.findByUserName(user.getUserName());
		if (existUser != null) {
				return null;
		}
			User newUser = new User();
			Long id = (userRepository.findMaxId() == null) ? 1l : userRepository.findMaxId() + 1;
			newUser.setId(id);
			newUser.setName(user.getName());
			newUser.setLastName(user.getLastName());
			newUser.setUserName(user.getUserName());
			newUser.setPassword(user.getPassword());
			newUser.setBookList(new ArrayList<>());
			userRepository.save(newUser);
			return newUser;
	}

	
	@Override
	public User getByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}
}

		
		


