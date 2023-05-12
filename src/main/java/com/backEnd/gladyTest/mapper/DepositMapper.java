package com.backEnd.gladyTest.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.backEnd.gladyTest.dto.DepositDto;
import com.backEnd.gladyTest.dto.DepositsFactory;
import com.backEnd.gladyTest.exceptions.DepositTypeDoesntExistExceptions;
import com.backEnd.gladyTest.model.Deposit;

@Mapper(componentModel = "spring")
public interface DepositMapper {

    DepositMapper INSTANCE = Mappers.getMapper(DepositMapper.class);

    @Named("depositToDepositDto")
    @Mapping(target = "depositType", source = "depositType")
    default DepositDto depositToDepositDto(Deposit deposit)
        throws DepositTypeDoesntExistExceptions {
        return DepositsFactory.getInstance().createDeposit(deposit.getDepositType(), deposit.getStartDate(), deposit.getAmount());
    }

    @Mapping(target = "depositType", source = "depositType")
    Deposit depositDtoToDeposit(DepositDto depositDto);
    
}
