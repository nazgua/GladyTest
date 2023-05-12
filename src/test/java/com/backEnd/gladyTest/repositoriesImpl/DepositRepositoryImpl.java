package com.backEnd.gladyTest.repositoriesImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.backEnd.gladyTest.model.Deposit;
import com.backEnd.gladyTest.repositories.DepositRepository;

@Service
public class DepositRepositoryImpl implements DepositRepository {

	private final Set<Deposit> depositSet;

	public DepositRepositoryImpl() {

		this.depositSet = new HashSet<>();
	}

	@Override
	public Deposit saveDeposit(Deposit deposit) {
		depositSet.add(deposit);
		return deposit;
	}

}
