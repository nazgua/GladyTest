package com.backEnd.gladyTest.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backEnd.gladyTest.dto.CompanyDto;
import com.backEnd.gladyTest.exceptions.CompanyNotFoundException;
import com.backEnd.gladyTest.mapper.CompanyMapper;
import com.backEnd.gladyTest.model.Company;
import com.backEnd.gladyTest.repositories.CompanyRepository;
import com.backEnd.gladyTest.services.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;
	private final CompanyMapper  companyMapper;

	@Autowired
	public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper  companyMapper) {
		this.companyRepository = companyRepository;
		this.companyMapper = companyMapper;
	}

	@Override
	public CompanyDto findCompanyDtoById(Long id) throws CompanyNotFoundException {
		Company company = companyRepository.findById(id);
		if (company == null) {
			throw new CompanyNotFoundException("Company not found with ID: " + id);
		}
		return companyMapper.companyToCompanyDto(company);
	}

	@Override
	public CompanyDto saveCompany(CompanyDto companyDto) {
		Company company = companyMapper.companyDtoToCompany(companyDto);
		companyRepository.saveCompany(company);
		return companyMapper.companyToCompanyDto(company);
	}

	@Override
	public CompanyDto updtaeCompany(CompanyDto companyDto) throws CompanyNotFoundException {
		Company company = companyMapper.companyDtoToCompany(companyDto);
		companyRepository.updateCompany(company);
		return companyDto;
	}
}
