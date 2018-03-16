package com.shop.service;

import com.shop.domain.User;
import com.shop.domain.UserBilling;
import com.shop.domain.UserPayment;
import com.shop.domain.UserShipping;
import org.springframework.stereotype.Service;

@Service
public interface MyProfileService{
        void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user);

        void updateUserShipping(UserShipping userShipping, User user);

        void setUserDefaultPayment(Long defaultPaymentId, User user);

        void setUserDefaultShipping(Long defaultShippingId, User user);
}
