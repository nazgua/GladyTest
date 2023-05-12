package com.backEnd.gladyTest.dto;

import java.util.Calendar;
import java.util.Date;

import com.backEnd.gladyTest.exceptions.DepositExpiredExceptions;
import com.backEnd.gladyTest.model.DepositType;

public class MealDepositsDto extends DepositDto {

	public MealDepositsDto(Date startDate, Long amount) {
		this.setDepositType(DepositType.MEAL);
		this.setStartDate(startDate);
		this.setAmount(amount);
		this.setEndDate(calculateEndDate());
	}

	@Override
	public Date calculateEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.MONTH, Calendar.FEBRUARY);
		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) + 1);
		cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
		return cal.getTime();

	}
}
