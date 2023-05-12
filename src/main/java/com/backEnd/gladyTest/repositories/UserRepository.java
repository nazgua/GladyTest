package com.backEnd.gladyTest.repositories;

import com.backEnd.gladyTest.exceptions.UserNotFoundException;
import com.backEnd.gladyTest.model.User;

public interface UserRepository {
	
	User findUserById(Long id);
	
	User updateUser(User user) throws UserNotFoundException;
	
	User saveUser(User user);
	
}
