package com.shop.service.impl;


import com.shop.configuration.AdminConf;
import com.shop.domain.User;
import com.shop.domain.dto.UserDto;
import com.shop.domain.security.PasswordResetToken;
import com.shop.mapper.UserMapper;
import com.shop.repository.PasswordResetTokenRepository;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import com.shop.service.SimpleEmailService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    private User user;

    private String token;

    private UserDto userDto;

    @Mock
    private PasswordResetTokenRepository passwordRTRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private PasswordResetToken passwordResetToken;


    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private AdminConf adminConf;


    @Before
    public void before() {
        user = new User(2L, "User_test",
                "firstName_test", "lastName_test",
                "test@test.pl", true, "password_test",null,null,
                "address_test", null,new ArrayList<>(),new ArrayList<>(), new HashSet<>());
        token = "test_token";
        userDto = new UserDto(2L, "User_test",
                "firstName_test", "lastName_test",
                "test@test.pl", true, "password_test",null,null,
                "address_test", null,new ArrayList<>(),new ArrayList<>(), new HashSet<>());

    }


    @Test
    public void testgetPasswordResetToken() throws Exception {
        //Given
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        when(passwordRTRepository.findByToken(token)).thenReturn(passwordResetToken);
        //When
        PasswordResetToken result = userService.getPasswordResetToken(token);
        //Then
        assertEquals("test_token", result.getToken());
        assertEquals("User_test", result.getUser().getUsername());
        assertEquals("firstName_test", result.getUser().getFirstName());
        assertEquals("lastName_test", result.getUser().getLastName());
        assertEquals("test@test.pl", result.getUser().getEmail());
        assertEquals("password_test", result.getUser().getPassword());
        assertEquals("address_test", result.getUser().getAddress());
    }



    @Test
    public void testFindByUsername() throws Exception {
        //Given
        String username= "User_test";
        when(userRepository.findByUsername(username)).thenReturn(user);
        when(userMapper.INSTANCE.userToUserDto(user)).thenReturn(userDto);
        //When
        UserDto result = userService.findByUsername(username);

        //Then
        assertEquals("User_test", result.getUsername());
        assertEquals("firstName_test", result.getFirstName());
        assertEquals("lastName_test", result.getLastName());
        assertEquals("test@test.pl", result.getEmail());
        assertEquals("password_test", result.getPassword());
        assertEquals("address_test", result.getAddress());

    }

    @Test
    public void testFindByEmail() throws Exception {
        //Given
        String email = "email_test";
        /*when(mapper.INSTANCE.userToUserDto(userRepository.findByEmail(email))).thenReturn(userDto);*/
        //When
        UserDto result = userService.findByEmail(email);
        /*UserDto result  = new UserDto();*/
        //Then
        /*assertEquals("User_test", result.getUsername());
        assertEquals("firstName_test", result.getFirstName());
        assertEquals("lastName_test", result.getLastName());
        assertEquals("test@test.pl", result.getEmail());
        assertEquals("password_test", result.getPassword());
        assertEquals("address_test", result.getAddress());*/
    }

    @Test
    public void testCreateUser() throws Exception {
        //Given
        String username = "test_username";
        HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
        String email = "test_email";
       /* when(mapper.INSTANCE.userToUserDto(userRepository.save(user))).thenReturn(userDto);*/
        //When
        /*UserDto result = userService.createUser(username,email,mockedRequest);*/
UserDto result = new UserDto();
        //Then
        /*assertEquals("User_test", result.getUsername());
        assertEquals("firstName_test", result.getFirstName());
        assertEquals("lastName_test", result.getLastName());
        assertEquals("test@test.pl", result.getEmail());
        assertEquals("password_test", result.getPassword());
        assertEquals("address_test", result.getAddress());*/

    }

    @Test
    public void save() throws Exception {
        //Given
       /* when(mapper.INSTANCE.userToUserDto(userRepository.save(user))).thenReturn(userDto);
        //When*/
        UserDto result  = userService.save(user);
        //Then
        assertEquals("User_test", result.getUsername());
        assertEquals("firstName_test", result.getFirstName());
        assertEquals("lastName_test", result.getLastName());
        assertEquals("test@test.pl", result.getEmail());
        assertEquals("password_test", result.getPassword());
        assertEquals("address_test", result.getAddress());
    }

    @Test
    public void forgetPassword() throws Exception {
        //Give
        String email = "email_test";
        HttpServletRequest  mockedRequest = Mockito.mock(HttpServletRequest.class);
        /*when(userMapper.userDtotoUser(userDto)).thenReturn(user);*/
        when(userService.save(user)).thenReturn(userDto);
        //When
        UserDto result  = userService.forgetPassword(email,userDto,mockedRequest);
        //Then
        assertEquals("User_test", result.getUsername());
        assertEquals("firstName_test", result.getFirstName());
        assertEquals("lastName_test", result.getLastName());
        assertEquals("test@test.pl", result.getEmail());
        assertEquals("password_test", result.getPassword());
        assertEquals("address_test", result.getAddress());

    }

}