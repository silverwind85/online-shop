package com.shop.service;

import com.shop.domain.UserShipping;
import com.shop.domain.dto.UserShippingDto;

import java.security.Principal;
import java.util.List;

public interface UserShippingService {
    UserShippingDto findById(Long id) throws Exception;

    UserShippingDto removeUserShipping(Long id , Principal principal) throws Exception;
    UserShippingDto UpdateUserShipping(Long id, Principal principal ) throws Exception;
    List<UserShippingDto> getUserShippings(Long id, Principal principal);
    UserShippingDto setupDefaultUserShipping(Long id,Principal principal ) throws Exception;
}
