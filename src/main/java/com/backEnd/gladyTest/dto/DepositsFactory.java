package com.backEnd.gladyTest.dto;

import java.util.Date;

import com.backEnd.gladyTest.exceptions.DepositTypeDoesntExistExceptions;
import com.backEnd.gladyTest.model.DepositType;

public class DepositsFactory {

	private static DepositsFactory instance = null;

	public static synchronized DepositsFactory getInstance() {
		if (instance == null) {
			instance = new DepositsFactory();
		}
		return instance;
	}

	public DepositDto createDeposit(Enum<DepositType> type, Date startDate, Long amount)
			throws DepositTypeDoesntExistExceptions {

		if (type.equals(DepositType.GIFT)) {
			return new GiftDepositsDto(startDate, amount);
		} else if (type.equals(DepositType.MEAL)) {
			return new MealDepositsDto(startDate, amount);
		}
		throw new DepositTypeDoesntExistExceptions("Deposit type " + type + " doesn't exist");
	}

}
