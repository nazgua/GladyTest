package com.backEnd.gladyTest.services.impl;

import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backEnd.gladyTest.dto.DepositDto;
import com.backEnd.gladyTest.dto.UserDto;
import com.backEnd.gladyTest.exceptions.DepositExpiredExceptions;
import com.backEnd.gladyTest.exceptions.UserNotFoundException;
import com.backEnd.gladyTest.mapper.UserMapper;
import com.backEnd.gladyTest.model.DepositType;
import com.backEnd.gladyTest.model.User;
import com.backEnd.gladyTest.repositories.UserRepository;
import com.backEnd.gladyTest.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	@Override
	public Long getDepositSumsByType(Long userId, DepositType depositType) {
		User user = userRepository.findUserById(userId);
		UserDto userDto = userMapper.userToUserDto(user);

		return userDto.getDepositsDto().stream().filter(d -> d.getDepositType() == depositType).mapToLong(d -> {
			try {
				return d.getAvailableAmountNow();
			} catch (DepositExpiredExceptions e) {
				log.error("Error: " + e.getMessage());
				return 0L;
			}
		}).sum();
	}

	@Override
	public Map<DepositType, Long> getUserDepositAmount(Long userId) {

		User user = userRepository.findUserById(userId);
		UserDto userDto = userMapper.userToUserDto(user);

		return userDto.getDepositsDto().stream()
				.collect(Collectors.groupingBy(DepositDto::getDepositType, Collectors.summingLong(d -> {
					try {
						return d.getAvailableAmountNow();
					} catch (DepositExpiredExceptions e) {
						log.error("Error: " + e.getMessage());
						return 0L;
					}
				})));
	}

	@Override
	public UserDto findById(Long id) throws UserNotFoundException {
		User user = userRepository.findUserById(id);
		if (user == null) {
			throw new UserNotFoundException("User not found with ID: " + id);
		}
		return userMapper.userToUserDto(user);
	}

	@Override
	public UserDto updateUser(UserDto userDto) throws UserNotFoundException {
		User user = userMapper.userDtoToUser(userDto);
		userRepository.updateUser(user);
		return userDto;
	}

}
