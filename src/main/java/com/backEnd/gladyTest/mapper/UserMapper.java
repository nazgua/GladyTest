package com.backEnd.gladyTest.mapper;

import com.backEnd.gladyTest.dto.DepositDto;
import com.backEnd.gladyTest.dto.DepositsFactory;
import com.backEnd.gladyTest.exceptions.DepositTypeDoesntExistExceptions;
import com.backEnd.gladyTest.model.Deposit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.backEnd.gladyTest.dto.UserDto;
import com.backEnd.gladyTest.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mapping(source = "deposits", target = "depositsDto")
	UserDto userToUserDto(User user);

	default List<DepositDto> mapDepositsList(List<Deposit> deposits) {
		return deposits.stream()
				.map(this::mapDeposit)
				.collect(Collectors.toList());
	}
	default DepositDto mapDeposit(Deposit deposit) {
		try {
			return DepositsFactory.getInstance().createDeposit(deposit.getDepositType(), deposit.getStartDate(), deposit.getAmount());
		} catch (DepositTypeDoesntExistExceptions depositTypeDoesntExistExceptions) {
			depositTypeDoesntExistExceptions.printStackTrace();
		}
		return null;
	}

	@Mapping(source = "depositsDto", target = "deposits")
	User userDtoToUser(UserDto userDto);

}