package com.backEnd.gladyTest.services;

import java.util.Date;

import com.backEnd.gladyTest.dto.DepositDto;
import com.backEnd.gladyTest.exceptions.CompanyNotFoundException;
import com.backEnd.gladyTest.exceptions.DepositTypeDoesntExistExceptions;
import com.backEnd.gladyTest.exceptions.InsufficientFundsExceptions;
import com.backEnd.gladyTest.exceptions.UserNotFoundException;
import com.backEnd.gladyTest.model.DepositType;

public interface DepositService {

	DepositDto createDeposit(DepositDto depositDto) throws DepositTypeDoesntExistExceptions;

	DepositDto distributeDeposit(Long companyId, Long userId, Enum<DepositType> type, Date startDate, Long amount)
			throws DepositTypeDoesntExistExceptions, CompanyNotFoundException, InsufficientFundsExceptions,
			UserNotFoundException;

}
