package com.backEnd.gladyTest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.backEnd.gladyTest.dto.UserDto;
import com.backEnd.gladyTest.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	
	UserDto userToUserDto(User user);

	User userDtoToUser(UserDto userDto);
}