package com.backEnd.gladyTest.dto;

import java.util.Objects;
import java.util.Set;

public class CompanyDto {

	private Long id;

	private Long balance;

	private String name;

	private Set<UserDto> usersDto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserDto> getUsersDto() {
		return usersDto;
	}

	public void setUsersDto(Set<UserDto> usersDto) {
		this.usersDto = usersDto;
	}

	public CompanyDto(Long id, Long balance, String name) {
		super();
		this.id = id;
		this.balance = balance;
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CompanyDto that = (CompanyDto) o;
		return Objects.equals(id, that.id) && Objects.equals(balance, that.balance)
				&& Objects.equals(name, that.name) && Objects.equals(usersDto,
				that.usersDto);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, balance, name, usersDto);
	}
}
