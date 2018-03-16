package com.shop.service;

import com.shop.domain.UserPayment;
import com.shop.domain.dto.UserPaymentDto;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.List;


public interface UserPaymentService {
    UserPaymentDto findById(Long id);
    UserPaymentDto removeCreditCard(Long id , Principal principal) throws Exception;
    UserPaymentDto UpdateCreditCard(Long id, Principal principal ) throws Exception;
    List<UserPaymentDto> getCreditCards(Long id, Principal principal);
    UserPaymentDto setupDefaultCreditCard(Long id ) throws Exception;
}
