package com.backEnd.gladyTest.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

	private Long id;

	private String firstName;

	private String lastName;

	private CompanyDto companyDto;

	private List<DepositDto> depositsDto = new ArrayList<DepositDto>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public CompanyDto getCompanyDto() {
		return companyDto;
	}

	public void setCompanyDto(CompanyDto companyDto) {
		this.companyDto = companyDto;
	}

	public List<DepositDto> getDepositsDto() {
		return depositsDto;
	}

	public void setDepositsDto(List<DepositDto> depositsDto) {
		this.depositsDto = depositsDto;
	}

	public UserDto(Long id, String firstName, String lastName) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
	}
}
