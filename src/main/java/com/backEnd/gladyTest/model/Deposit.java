package com.backEnd.gladyTest.model;

import java.util.Calendar;
import java.util.Date;

import com.backEnd.gladyTest.exceptions.DepositExpiredExceptions;
import com.backEnd.gladyTest.utils.DateUtils;


public class Deposit {
	
	protected Date startDate;
	
	protected Date endDate;
	
	protected Long amount;
	
	protected DepositType depositType;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public DepositType getDepositType() {
		return depositType;
	}

	public void setDepositType(DepositType depositType) {
		this.depositType = depositType;
	}

	public Long getAvailableAmountNow() throws DepositExpiredExceptions {
		Date currentDate = Calendar.getInstance().getTime();
		if (currentDate.after(startDate) && currentDate.before(endDate))
			return amount;
		else
			throw new DepositExpiredExceptions("The deposit has expired");
	}

	@Override
	public String toString() {

		return this.depositType + " with the Amount of " + this.amount + ", with a the date of start of "
				+ DateUtils.dateToStringParse(this.startDate) + " and an end date of "
				+ DateUtils.dateToStringParse(this.endDate) + "\n";
	}

}
