package com.shop.domain.security;


import com.shop.domain.User;
import com.shop.repository.PasswordResetTokenRepository;
import com.shop.utility.SecurityUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordResetTokenTest {

    @Autowired
    private PasswordResetTokenRepository passwordRe;

    @Test
    public void testCalculateExpiryDate(){

        //Given
        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        User user = new User(2L,"User-test",
                "firstName_test","lastName_test",
                "test@test.pl",true,"password_test",null,null,
                "address_test",null,new ArrayList<>(),new ArrayList<>(),new HashSet<>() );
        user.setPassword(encryptedPassword);
        String token =UUID.randomUUID().toString();
        //When
        PasswordResetToken passwordResetToken = new PasswordResetToken(token,user);
        LocalDate today = LocalDate.now();

        //Then
        Assert.assertEquals(today.plusDays(1),passwordResetToken.getExpiryDate());
    }


}