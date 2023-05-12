package com.backEnd.gladyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backEnd.gladyTest.dto.CompanyDto;
import com.backEnd.gladyTest.mapper.CompanyMapper;
import com.backEnd.gladyTest.repositoriesImpl.CompanyRepositoryImpl;
import com.backEnd.gladyTest.services.CompanyService;
import com.backEnd.gladyTest.services.impl.CompanyServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CompanyTests {

  @Mock
  private CompanyService companyService;

  @InjectMocks
  private CompanyRepositoryImpl companyRepository;

  @Spy
  private CompanyMapper companyMapper = Mappers.getMapper(CompanyMapper.class);

  @BeforeEach
  public void init() {
    this.companyService = new CompanyServiceImpl(companyRepository, companyMapper);
    }

  @Test
  public void testSaveCompany_newElement_shouldSuccess() {
    CompanyDto companyDto = new CompanyDto(99L, 10000L, "Test Company");
    CompanyDto saved = this.companyService.saveCompany(companyDto);
    assertNotNull(saved);
    assertEquals(saved, companyDto);
  }
}
