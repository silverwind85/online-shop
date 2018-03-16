package com.shop.service;



import com.shop.domain.User;
import com.shop.domain.UserBilling;
import com.shop.domain.UserPayment;
import com.shop.domain.UserShipping;
import com.shop.domain.dto.UserDto;
import com.shop.domain.security.PasswordResetToken;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    PasswordResetToken getPasswordResetToken(final String token);

    void  createPasswordResetTokenForUser(final User user, final String token);

    UserDto findByUsername(String username);

    UserDto findByEmail(String email);

    UserDto createUser(String username, String email, HttpServletRequest request) throws Exception;

    UserDto save(User user);

    UserDto forgetPassword(final String email, final UserDto user, final HttpServletRequest request);

    UserDto confirmUser(final User user);
    User findByUsernameU(String Username);

    User update(final UserDto userDto );



}
