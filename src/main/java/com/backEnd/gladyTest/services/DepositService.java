package com.backEnd.gladyTest.services;

import java.util.Date;

import com.backEnd.gladyTest.dto.DepositDto;
import com.backEnd.gladyTest.exceptions.*;
import com.backEnd.gladyTest.model.DepositType;

public interface DepositService {

	DepositDto createDeposit(DepositDto depositDto) throws DepositTypeDoesntExistExceptions;

	DepositDto distributeDeposit(Long companyId, Long userId, Enum<DepositType> type, Date startDate, Long amount)
			throws DepositTypeDoesntExistExceptions, CompanyNotFoundException, InsufficientFundsExceptions,
			UserNotFoundException, DepositExpiredExceptions;

}
