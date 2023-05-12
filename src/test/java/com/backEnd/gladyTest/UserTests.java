package com.backEnd.gladyTest;

import com.backEnd.gladyTest.dto.DepositDto;
import com.backEnd.gladyTest.dto.DepositsFactory;
import com.backEnd.gladyTest.dto.UserDto;
import com.backEnd.gladyTest.exceptions.DepositTypeDoesntExistExceptions;
import com.backEnd.gladyTest.exceptions.UserNotFoundException;
import com.backEnd.gladyTest.mapper.UserMapper;
import com.backEnd.gladyTest.model.DepositType;
import com.backEnd.gladyTest.repositoriesImpl.UserRepositoryImpl;
import com.backEnd.gladyTest.services.DepositService;
import com.backEnd.gladyTest.services.UserService;
import com.backEnd.gladyTest.services.impl.UserServiceImpl;
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

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class UserTests {

    private Logger log = LoggerFactory.getLogger(UserTests.class);

    @Mock
    private UserService userService;

//    @Mock
//    private DepositService distributeService;

    @InjectMocks
    private UserRepositoryImpl userRepository;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @BeforeEach
    public void init() {
        this.userService = new UserServiceImpl(userRepository, userMapper);
    }

    @Test
    public void testUserDepositAmountShouldSuccess() throws UserNotFoundException, DepositTypeDoesntExistExceptions {
        UserDto userDto = userService.findById(1L);
        DepositDto firstDepositDto = DepositsFactory.getInstance().createDeposit(DepositType.MEAL, new Date(), 200L);
        DepositDto secondDepositDto = DepositsFactory.getInstance().createDeposit(DepositType.MEAL, new Date(), 200L);
        userDto.getDepositsDto().add(firstDepositDto);
        userDto.getDepositsDto().add(secondDepositDto);

        userService.updateUser(userDto);

        log.info(userService.getUserDepositAmount(1L).toString());

    }
}
