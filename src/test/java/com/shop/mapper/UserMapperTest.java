package com.shop.mapper;

import com.shop.domain.User;
import com.shop.domain.UserPayment;
import com.shop.domain.UserShipping;
import com.shop.domain.dto.UserDto;
import com.shop.mapper.UserMapper;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;


public class UserMapperTest {


private UserMapper mapper ;

    private User user;
    private UserDto userDto;
    private UserPayment userPayment;
    private UserShipping userShipping;
    private List<UserPayment> userPayments=new ArrayList<>();
    private List<UserShipping> userShippings=new ArrayList<>();



    @Before
    public void before(){


        userPayment = new UserPayment(1L,"test_type","test_card","99999999",12,2012,234,"holder_test",true,user,null);
        userPayments.add(userPayment);
        userShipping = new UserShipping(1L,"test_shipping","test_street1","test_street2","test_city","voivodeship_test","test_country","43-440",true,user);
        userShippings.add(userShipping);
        user = new User(2L, "User_test",
                "firstName_test", "lastName_test",
                "test@test.pl", true, "password_test",null,null,
                "address_test", null,userShippings,userPayments, new HashSet<>());

        userDto = new UserDto(2L, "User_test",
                "firstName_test", "lastName_test",
                "test@test.pl", true, "password_test",null,null,
                "address_test", null,
                new ArrayList<>(),new ArrayList<>(),new HashSet<>());
    }

    @Test
    public void testUserMapStrut(){
        //When //Give
        UserDto userDto = mapper.INSTANCE.userToUserDto(user);
        //Then
        assertEquals("address_test",userDto.getAddress());
        assertThat( userDto ).isNotNull();
        assertThat( userDto.getId() ).isEqualTo( 2L );
        assertThat( userDto.getUsername() ).isEqualTo( "User_test" );
        assertThat( userDto.getFirstName() ).isEqualTo( "firstName_test" );
    }
}
