package com.backEnd.gladyTest.services.impl;

import java.util.Date;
import java.util.List;

import com.backEnd.gladyTest.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backEnd.gladyTest.dto.CompanyDto;
import com.backEnd.gladyTest.dto.DepositDto;
import com.backEnd.gladyTest.dto.DepositsFactory;
import com.backEnd.gladyTest.dto.UserDto;
import com.backEnd.gladyTest.mapper.DepositMapper;
import com.backEnd.gladyTest.model.Deposit;
import com.backEnd.gladyTest.model.DepositType;
import com.backEnd.gladyTest.repositories.DepositRepository;
import com.backEnd.gladyTest.services.CompanyService;
import com.backEnd.gladyTest.services.DepositService;
import com.backEnd.gladyTest.services.UserService;

@Service
public class DepositServiceImpl implements DepositService {

	private Logger log = LoggerFactory.getLogger(DepositServiceImpl.class);

	private final DepositRepository depositRepository;
	private final CompanyService companyService;
	private final UserService userService;
	private final DepositMapper depositMapper;

	@Autowired
	public DepositServiceImpl(DepositRepository depositRepository, CompanyService companyService,
			UserService userService, DepositMapper depositMapper) {
		this.depositRepository = depositRepository;
		this.companyService = companyService;
		this.userService = userService;
		this.depositMapper = depositMapper;
	}

	@Override
	public DepositDto createDeposit(DepositDto depositDto) throws DepositTypeDoesntExistExceptions {
		Deposit deposit = depositMapper.depositDtoToDeposit(depositDto);
		depositRepository.saveDeposit(deposit);
		return depositMapper.depositToDepositDto(deposit);
	}

	@Override
	public DepositDto distributeDeposit(Long companyId, Long userId, Enum<DepositType> type, Date startDate,
			Long amount) throws DepositTypeDoesntExistExceptions, CompanyNotFoundException, InsufficientFundsExceptions,
			UserNotFoundException, DepositExpiredExceptions {
		if(startDate.before(new Date())){
			throw new DepositExpiredExceptions("The start date msut be after or equals to the date of today");
		}
		CompanyDto companyDto = companyService.findCompanyDtoById(companyId);
		if (companyDto.getBalance() < amount) {
			throw new InsufficientFundsExceptions("There are no funds available for this amount of deposit");
		}
		companyDto.setBalance(companyDto.getBalance() - amount);
		companyService.updtaeCompany(companyDto);

		DepositsFactory depositsFactory = DepositsFactory.getInstance();
		DepositDto depositDto = depositsFactory.createDeposit(type, startDate, amount);
		depositDto = createDeposit(depositDto);

		UserDto userDto = userService.findById(userId);
		List<DepositDto> depositDtoList = userDto.getDepositsDto();
		depositDtoList.add(depositDto);
		userDto.setDepositsDto(depositDtoList);
		userService.updateUser(userDto);

		log.info("The company offered to " + userDto.getFirstName() + " " + userDto.getLastName() + " a deposit "
				+ depositDto.getDepositType() + " of " + depositDto.getAmount() + "$");

		return depositDto;
	}
}
