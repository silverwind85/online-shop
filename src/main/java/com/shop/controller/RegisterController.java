package com.shop.controller;




import com.shop.domain.User;
import com.shop.domain.UserBilling;
import com.shop.domain.UserPayment;
import com.shop.domain.UserShipping;
import com.shop.domain.dto.UserDto;
import com.shop.domain.security.PasswordResetToken;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import com.shop.service.UserPaymentService;
import com.shop.service.UserService;
import com.shop.service.UserShippingService;
import com.shop.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class RegisterController {


    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private UserShippingService userShippingService;


    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("classActiveIndex","active");
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "user/form/myAccount";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(HttpServletRequest request,
                           @ModelAttribute("email") String email,
                           @ModelAttribute("username") String username,
                           Model model) throws Exception {

        model.addAttribute("classActiveRegister", true);
        model.addAttribute("email", email);
        model.addAttribute("username", username);

        if (userService.findByUsername(username) != null) {
            model.addAttribute("usernameExists", true);
            return "user/form/myAccount";
        }
        if (userService.findByEmail(email) != null) {
            model.addAttribute("emailExists", true);
            return "user/form/myAccount";
        }
        userService.createUser(username,email,request);
        model.addAttribute("emailSent", "true");

        return "user/form/myAccount";
    }

    @RequestMapping("/confirm")
    public String confirm(Model model, @RequestParam("token") String token) {
        PasswordResetToken passToken = userService.getPasswordResetToken(token);

        if (passToken == null) {
            String message = "Invalid Token";
            model.addAttribute("message", message);
            return "redirect:/badRequest";
        }
         UserDto userDto = userService.confirmUser(passToken.getUser());
        model.addAttribute("user", userDto);

        model.addAttribute("classActiveEdit", true);
        return "user/form//myProfile";
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public String forgetPassword(HttpServletRequest request,
                                 Model model,
                                 @ModelAttribute("email") String email) {
        model.addAttribute("classActiveForgetPassword",true);
        UserDto userDto = userService.findByEmail(email);

        if (userDto == null) {
            model.addAttribute("emailExists",true);
                    return "myAccount";
        }
        userService.forgetPassword(email,userDto,request);

        model.addAttribute("forgetPasswordEmailSent",true);

        return "user/form/myAccount";


    }


}