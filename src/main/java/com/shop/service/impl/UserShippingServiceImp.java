package com.shop.service.impl;

import com.shop.domain.User;
import com.shop.domain.UserPayment;
import com.shop.domain.UserShipping;
import com.shop.domain.dto.UserDto;
import com.shop.domain.dto.UserPaymentDto;
import com.shop.domain.dto.UserShippingDto;
import com.shop.mapper.UserMapper;
import com.shop.mapper.UserShippingMapper;
import com.shop.repository.UserShippingRepository;
import com.shop.service.UserService;
import com.shop.service.UserShippingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;


@Service
public class UserShippingServiceImp implements UserShippingService {

    private static final Logger LOGGER= LoggerFactory.getLogger(UserShippingService.class);

    @Autowired
    private UserShippingRepository userShippingRepository;

    @Autowired
    private UserService userService;

    private UserShippingMapper userShippingMapper;


    private UserMapper userMapper;

    @Override
    public UserShippingDto findById(Long id) throws Exception {
        UserShipping userShipping = userShippingRepository.findById(id);
        if(userShipping==null){
            LOGGER.info("Not found");
            throw new Exception("Not found");
        }
        return userShippingMapper.INSTANCE.userShippingtToUserShippingDto(userShipping);
    }


    @Override
    public UserShippingDto removeUserShipping(Long id, Principal principal) throws Exception {
        UserDto userdto = userService.findByUsername(principal.getName());
        UserShippingDto userShippingDto= findById(id);
        if(userdto.getId()!=userShippingDto.getUser().getId()){
            LOGGER.info("User id not equal UserPayment id");
            throw new Exception();
        }
        userShippingRepository.delete(id);

        return userShippingDto;

    }

    @Override
    public UserShippingDto UpdateUserShipping(Long id, Principal principal) throws Exception {
        UserDto userDto = userService.findByUsername(principal.getName());
        UserShippingDto userShippingDto = findById(id);
        UserShipping userShipping = userShippingMapper.INSTANCE.userShippingDtoToUserShipping(userShippingDto);
        User user = userMapper.INSTANCE.userDtotoUser(userDto);
        if(user.getId()!=userShipping.getUser().getId()){
            LOGGER.info("User id not equal UserPayment id");
            throw new Exception();
        }
        userShipping.setUser(user);
        user.getUserShippings().add(userShipping);
        userService.save(user);

        return userShippingDto;
    }

    @Override
    public List<UserShippingDto> getUserShippings(Long id, Principal principal) {
        UserDto userDto = userService.findByUsername(principal.getName());
        return userDto.getUserShippingDtos();
    }

    @Override
    public UserShippingDto setupDefaultUserShipping(Long id, Principal principal) throws Exception {
        UserShipping userShipping = userShippingRepository.findById(id);
       if(userShipping==null){
           LOGGER.info("Not found");
           throw new Exception("Not found");
       }

       userShipping.setUserShippingDefault(true);
        userShippingRepository.save(userShipping);
        UserShippingDto userShippingDto = userShippingMapper.INSTANCE.userShippingtToUserShippingDto(userShipping);

       return userShippingDto;
    }


}
