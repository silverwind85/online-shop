package com.shop.domain;


import com.shop.domain.security.Role;
import com.shop.domain.security.UserRole;
import com.shop.repository.UserRepository;
import com.shop.service.UserService;
import com.shop.utility.SecurityUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;


/*@RunWith(SpringRunner.class)
@SpringBootTest*/
public class UserTestSuite {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() throws Exception {
       /* //Given
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Adams");
        user1.setUsername("j");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user1.setEmail("slcieslar@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1 = new Role();
        role1.setId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));
        //Then
        userRepository.save(user1);
        //When
        Assert.assertEquals("John",user1.getFirstName());
        Assert.assertEquals("Adams",user1.getLastName());
        Assert.assertEquals("j",user1.getUsername());
        Assert.assertEquals("slcieslar@gmail.com",user1.getEmail());
        long id = user1.getId();
        userRepository.delete(id);*/
    }


}