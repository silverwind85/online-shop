package com.shop.service.impl;

import com.shop.domain.User;
import com.shop.domain.UserPayment;
import com.shop.domain.dto.UserDto;
import com.shop.domain.dto.UserPaymentDto;
import com.shop.mapper.UserMapper;
import com.shop.mapper.UserPaymentMapper;
import com.shop.repository.UserPaymentRepository;
import com.shop.service.UserPaymentService;
import com.shop.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserPaymentServiceImp implements UserPaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPayment.class);

    private UserPaymentMapper userPaymentMapper;

    private UserMapper userMaper;

    private UserDto userDto;

    @Autowired
    private UserService userService;

    @Autowired
    private UserPaymentRepository userPaymentRepository;

    @Override
    public UserPaymentDto findById(Long id) {
        UserPayment userPayment = userPaymentRepository.findById(id);
        if(userPayment==null){
            LOGGER.info("Not found");
        }
        return userPaymentMapper.INSTANCE.userPaymentToUserPaymentDto(userPayment);

    }

    @Override
    public UserPaymentDto removeCreditCard(Long id, Principal principal) throws Exception {
        UserDto userdto = userService.findByUsername(principal.getName());
        UserPaymentDto userPaymentDto = findById(id);
        if(userdto.getId()!=userPaymentDto.getUser().getId()){
            LOGGER.info("User id not equal UserPayment id");
            throw new Exception();
        }
        userPaymentRepository.delete(id);

        return userPaymentDto;
    }

    @Override
    public UserPaymentDto UpdateCreditCard(Long id, Principal principal) throws Exception {

        UserDto userDto = userService.findByUsername(principal.getName());
        UserPaymentDto userPaymentDto = findById(id);
        UserPayment userPayment = userPaymentMapper.INSTANCE.userPaymentDtoToUserPayment(userPaymentDto);
        User user = userMaper.INSTANCE.userDtotoUser(userDto);
        if(user.getId()!=userPayment.getUser().getId()){
            LOGGER.info("User id not equal UserPayment id");
            throw new Exception();
        }
        userPayment.setUser(user);
        user.getUserPayments().add(userPayment);
        userService.save(user);

        return userPaymentDto;
    }

    @Override
    public List<UserPaymentDto> getCreditCards(Long id, Principal principal) {
        UserDto userDto = userService.findByUsername(principal.getName());

        return userDto.getUserPaymentDtos();
    }

    @Override
    public UserPaymentDto setupDefaultCreditCard(Long id) throws Exception {
        UserPayment userPayment = userPaymentRepository.findById(id);
        if(userPayment==null){
            LOGGER.info("No found");
            throw new Exception();
        }
        userPayment.setDefaultPayment(true);
        userPaymentRepository.save(userPayment);

        return userPaymentMapper.INSTANCE.userPaymentToUserPaymentDto(userPayment);
    }



}
