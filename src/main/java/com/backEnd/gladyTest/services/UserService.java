package com.backEnd.gladyTest.services;

import java.util.Map;

import com.backEnd.gladyTest.dto.UserDto;
import com.backEnd.gladyTest.exceptions.UserNotFoundException;
import com.backEnd.gladyTest.model.DepositType;

public interface UserService {
    
    Long getDepositSumsByType(Long userId, DepositType depositType);
    
    Map<DepositType, Long> getUserDepositAmount(Long userId);
    
    UserDto findById(Long id) throws UserNotFoundException;
    
    UserDto updateUser(UserDto userDto) throws UserNotFoundException;

}