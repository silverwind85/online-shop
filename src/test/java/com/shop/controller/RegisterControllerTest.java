package com.shop.controller;


import com.shop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)*/

public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void index() throws Exception {
       /* mockMvc.perform(get("/"))
                .andExpect(status().is(200));*/
    }

    @Test
    public void login() throws Exception {
    }

    @Test
    public void register() throws Exception {
    }

    @Test
    public void confirm() throws Exception {
    }

    @Test
    public void forgetPassword() throws Exception {
    }

}