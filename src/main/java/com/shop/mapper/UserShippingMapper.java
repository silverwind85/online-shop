package com.shop.mapper;

import com.shop.domain.UserShipping;
import com.shop.domain.dto.UserShippingDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface UserShippingMapper {

    UserShippingMapper INSTANCE = Mappers.getMapper(UserShippingMapper.class);

    UserShippingDto userShippingtToUserShippingDto(UserShipping userShipping);

    UserShipping userShippingDtoToUserShipping(UserShippingDto userShippingDto);
}
