package com.backEnd.gladyTest.repositoriesImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.backEnd.gladyTest.exceptions.CompanyNotFoundException;
import com.backEnd.gladyTest.model.Company;
import com.backEnd.gladyTest.repositories.CompanyRepository;

@Service
public class CompanyRepositoryImpl implements CompanyRepository {

	private final Set<Company> companyList;

	public CompanyRepositoryImpl() {
		// Initialize the companyMap
		this.companyList = new HashSet<Company>(){{
			add(new Company(1L, 2000L, "Test 1 Company"));
		}};

	}

	@Override
	public Company findById(Long id) {
		// Retrieve the company from the companyMap based on the provided ID
		if (id == null) {
			throw new IllegalArgumentException("Company id cannot be null.");
		}
		return companyList.stream().filter(c -> c.getId().equals(id)).findAny().orElse(null);
	}

	@Override
	public Company saveCompany(Company company) {
		Long id = company.getId();
		if (id == null) {
			throw new IllegalArgumentException("A company id must be provided.");
		}
		if (findById(id) != null) {
			throw new IllegalArgumentException("A company with the same id already exists.");
		}
		companyList.add(company);
		return company;
	}

	@Override
	public Company updateCompany(Company company) throws CompanyNotFoundException {
		Company existingCompany = findById(company.getId());
		if (existingCompany == null) {
			throw new CompanyNotFoundException("Company not found with ID: " + company.getId());
		}
		return saveExistingCompany(existingCompany);
	}

	public Company saveExistingCompany(Company existingCompany) {
		for (Company company : this.companyList) {
			if (company.getId() == existingCompany.getId()) {
				company.setBalance(existingCompany.getBalance());
				return company;
			}
		}
		throw new IllegalArgumentException("Company not found in the list");
	}
}
