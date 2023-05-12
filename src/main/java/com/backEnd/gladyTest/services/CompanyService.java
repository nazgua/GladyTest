package com.backEnd.gladyTest.services;

import com.backEnd.gladyTest.dto.CompanyDto;
import com.backEnd.gladyTest.exceptions.CompanyNotFoundException;

public interface CompanyService {

	CompanyDto findCompanyDtoById(Long id) throws CompanyNotFoundException;

	CompanyDto saveCompany(CompanyDto companyDto);
	
	CompanyDto updtaeCompany(CompanyDto companyDto) throws CompanyNotFoundException;
}
