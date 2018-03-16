package com.shop.mapper;


import com.shop.domain.User;
import com.shop.domain.dto.UserDto;
import org.mapstruct.Mapper;

import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface UserMapper {

        UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
        @Mapping(target = "userPaymentDtos", source = "userPayments")
        @Mapping(target = "userShippingDtos", source = "userShippings")
        @Mapping(target = "userRoleDtos", source = "userRoles")
        UserDto userToUserDto(User user);
        @Mapping(target = "userPayments", source = "userPaymentDtos")
        @Mapping(target = "userShippings", source = "userShippingDtos")
        @Mapping(target = "userRoles", source = "userRoleDtos")
        User userDtotoUser(UserDto userDto);

}
