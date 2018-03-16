package com.shop.mapper;

import com.shop.domain.UserBilling;
import com.shop.domain.dto.UserBillingDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface UserBillingMapper {
    UserBillingMapper INSTANCE= Mappers.getMapper(UserBillingMapper.class);

    UserBillingDto UserBillingToUserBillingDto(UserBilling userBilling);
    UserBilling UserBillingDtoToUserBilling(UserBilling userBilling);

}
