package com.backEnd.gladyTest.repositories;

import com.backEnd.gladyTest.exceptions.CompanyNotFoundException;
import com.backEnd.gladyTest.model.Company;

public interface CompanyRepository {

	Company findById(Long id);

	Company saveCompany(Company company);
	
	Company updateCompany(Company company) throws CompanyNotFoundException;

}
