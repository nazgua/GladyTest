package com.backEnd.gladyTest.dto;

import java.util.Calendar;
import java.util.Date;

import com.backEnd.gladyTest.model.DepositType;

public class GiftDepositsDto extends DepositDto {

	public GiftDepositsDto(Date startDate, Long amount) {

		this.setDepositType(DepositType.GIFT);
		this.setAmount(amount);
		this.setStartDate(startDate);
		this.setEndDate(calculateEndDate());
	}

	@Override
	public Date calculateEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.DATE, 364);
		return cal.getTime();
	}
}
