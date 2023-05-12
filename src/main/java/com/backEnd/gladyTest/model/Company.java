package com.backEnd.gladyTest.model;

import java.util.Set;

public class Company {

	private Long id;

	private Long balance;

	private String name;

	private Set<User> users;

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

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Company(Long id, Long balance, String name) {
		super();
		this.id = id;
		this.balance = balance;
		this.name = name;
	}
}
