package com.backEnd.gladyTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.backEnd.gladyTest.dto.CompanyDto;
import com.backEnd.gladyTest.dto.DepositDto;
import com.backEnd.gladyTest.dto.DepositsFactory;
import com.backEnd.gladyTest.dto.UserDto;
import com.backEnd.gladyTest.exceptions.CompanyNotFoundException;
import com.backEnd.gladyTest.exceptions.DepositTypeDoesntExistExceptions;
import com.backEnd.gladyTest.exceptions.InsufficientFundsExceptions;
import com.backEnd.gladyTest.exceptions.UserNotFoundException;
import com.backEnd.gladyTest.mapper.CompanyMapper;
import com.backEnd.gladyTest.mapper.DepositMapper;
import com.backEnd.gladyTest.mapper.UserMapper;
import com.backEnd.gladyTest.model.DepositType;
import com.backEnd.gladyTest.repositoriesImpl.CompanyRepositoryImpl;
import com.backEnd.gladyTest.repositoriesImpl.DepositRepositoryImpl;
import com.backEnd.gladyTest.repositoriesImpl.UserRepositoryImpl;
import com.backEnd.gladyTest.services.CompanyService;
import com.backEnd.gladyTest.services.DepositService;
import com.backEnd.gladyTest.services.UserService;
import com.backEnd.gladyTest.services.impl.CompanyServiceImpl;
import com.backEnd.gladyTest.services.impl.DepositServiceImpl;
import com.backEnd.gladyTest.services.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DepositTests {

	private Logger log = LoggerFactory.getLogger(DepositTests.class);

	@Mock
	private CompanyService companyService;

	@Mock
	private UserService userService;

	@Mock
	private DepositService depositService;
	
	@Spy
	private DepositMapper depositMapper = Mappers.getMapper(DepositMapper.class);

	@InjectMocks
	private DepositRepositoryImpl depositRepository;

	@BeforeEach
	public void init() {
		this.depositService = new DepositServiceImpl(depositRepository, companyService, userService, depositMapper);
	}

	@Test
	public void testDistribute_notEnoughMoney_shouldThrowException() throws CompanyNotFoundException, DepositTypeDoesntExistExceptions, InsufficientFundsExceptions, UserNotFoundException {

		// Mock companyDto
		CompanyDto companyDto = new CompanyDto(1L, 200L, "Google");
		when(companyService.findCompanyDtoById(1L)).thenReturn(companyDto);

		// Call the method
		assertThrows(InsufficientFundsExceptions.class, () -> {
			DepositDto result = depositService.distributeDeposit(1L, 1L, DepositType.GIFT, new Date(), 500L);
		});
	}

	@Test
	public void testDistribute_coherentDistribution_shouldReturnSuccess() throws Exception {
		// Mock companyDto
		CompanyDto companyDto = new CompanyDto(1L, 1500L, "Google");
		when(companyService.findCompanyDtoById(1L)).thenReturn(companyDto);

		// Mock userDto
		UserDto userDto = new UserDto(1L, "John", "Doe");
		List<DepositDto> depositDtoList = new ArrayList<>();
		userDto.setDepositsDto(depositDtoList);
		when(userService.findById(1L)).thenReturn(userDto);

		// Call the method
		DepositDto result = depositService.distributeDeposit(1L, 1L, DepositType.GIFT, new Date(), 500L);
		Long expectedCompanyBalance = 1000L;

		// Assertions
		assertNotNull(result);
		assertEquals(DepositType.GIFT, result.getDepositType());
		assertEquals(500L, result.getAmount().longValue());
		assertEquals(1, userDto.getDepositsDto().size());
		assertEquals(expectedCompanyBalance, companyDto.getBalance().longValue());

	}

	@Test
	public void testEndDateCalculationForGiftDeposit() throws DepositTypeDoesntExistExceptions {
		Date startDate = getDate("06/15/2021");
		DepositsFactory depositsFactory = DepositsFactory.getInstance();
		DepositDto depositGiftDto = depositsFactory.createDeposit(DepositType.GIFT, startDate, 1L);

		// Calculate the expected end date
		Date expectedGiftEndDate = getDate("06/14/2022");
		assertEquals(expectedGiftEndDate, depositGiftDto.getEndDate());
	}

	@Test
	public void testEndDateCalculationForMealDeposit() throws DepositTypeDoesntExistExceptions {
		Date startDate = getDate("01/01/2020");
		DepositsFactory depositsFactory = DepositsFactory.getInstance();
		DepositDto depositMealDto = depositsFactory.createDeposit(DepositType.MEAL, startDate, 1L);

		// Calculate the expected end date
		Date expectedMealEndDate = getDate("02/28/2021");
		assertEquals(expectedMealEndDate, depositMealDto.getEndDate());
	}

	@Test
	public void testCreateDepositForInvalidType() {
		// Assert that the exception is thrown
		assertThrows(DepositTypeDoesntExistExceptions.class, () -> {
			DepositsFactory depositsFactory = DepositsFactory.getInstance();
			depositsFactory.createDeposit(DepositType.INVALID_TYPE, getDate("02/28/2021"), 1L);
		});
	}

	private Date getDate(String dateString) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			return formatter.parse(dateString);
		} catch (ParseException e) {
			throw new RuntimeException("Failed to parse date: " + dateString, e);
		}
	}
}
