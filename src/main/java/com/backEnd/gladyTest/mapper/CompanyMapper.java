package com.backEnd.gladyTest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.backEnd.gladyTest.dto.CompanyDto;
import com.backEnd.gladyTest.model.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    CompanyDto companyToCompanyDto(Company company);

    Company companyDtoToCompany(CompanyDto companyDto);
}