package com.shop.mapper;

import com.shop.domain.UserPayment;
import com.shop.domain.dto.UserPaymentDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel="spring")
public interface UserPaymentMapper {

    UserPaymentMapper INSTANCE = Mappers.getMapper(UserPaymentMapper.class);

    List<UserPaymentDto> userPaymentsToUserPaymentDtos(List<UserPayment> userPayments);
    UserPaymentDto userPaymentToUserPaymentDto(UserPayment userPayment);
    List<UserPayment> userPaymentDtosToUserPayments(List<UserPaymentDto> userPaymentDtos);
    UserPayment userPaymentDtoToUserPayment(UserPaymentDto userPaymentDto);

}
