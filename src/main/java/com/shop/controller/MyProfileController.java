package com.shop.controller;

import com.shop.domain.User;
import com.shop.domain.UserBilling;
import com.shop.domain.UserPayment;
import com.shop.domain.UserShipping;
import com.shop.domain.dto.ProductDto;
import com.shop.domain.dto.UserDto;
import com.shop.service.MyProfileService;
import com.shop.service.UserPaymentService;
import com.shop.service.UserService;
import com.shop.service.UserShippingService;
import com.shop.utility.USConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Controller
public class MyProfileController {



    @Autowired
    private UserService userService;

    @Autowired
    private MyProfileService myProfileService;

    @Autowired
    private UserPaymentService userPaymentService;

    @Autowired
    private UserShippingService userShippingService;


    @GetMapping("/myProfile")
    public String myProfile(Model model, Principal principal){
        UserDto userDto= userService.findByUsername(principal.getName());

        if(!model.containsAttribute("user")){
            model.addAttribute("user",userDto);
        }

       /* model.addAttribute("userPayment", userDto.getUserPaymentDtos());
        model.addAttribute("userShipping", userDto.getUserShippingDtos());*/
		/*model.addAttribute("orderList", user.getOrderList());*/

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("listOfShippingAddresses", true);

        model.addAttribute("classActiveEdit", true);

        return "user/form/myProfile";

    }
    @PostMapping("users/edit")
    public String updateUser( Model model,@ModelAttribute @Valid UserDto userDto, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.user", result);
            redirectAttributes.addFlashAttribute("user", userDto);
            return ("redirect:/myProfile");
        } else {

            userService.update(userDto);
            return "redirect:/myProfile";
        }
    }

    /*@RequestMapping("/listOfCreditCards")
    public String listOfCreditCards(
            Model model, Principal principal, HttpServletRequest request
    ) {
        User user = userService.findByUsernameU(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPayments());
        model.addAttribute("userShippingList", user.getUserShippings());
	*//*	model.addAttribute("orderList", user.orderList());*//*

        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        return "user/form/myProfile";
    }

    @RequestMapping("/listOfShippingAddresses")
    public String listOfShippingAddresses(
            Model model, Principal principal, HttpServletRequest request
    ) {
        UserDto userDto = userService.findByUsername(principal.getName());

        model.addAttribute("user", userDto);
        model.addAttribute("userPaymentList", userDto.getUserPaymentDtos());
        model.addAttribute("userShippingList", userDto.getUserShippingDtos());
		*//*model.addAttribute("orderList", user.orderList());*//*

        model.addAttribute("listOfCreditCards", true);

        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("classActiveShipping", true);

        return "user/form/myProfile";
    }

    @RequestMapping("/addNewCreditCard")
    public String addNewCreditCard(
            Model model, Principal principal
    ){
        UserDto userDto = userService.findByUsername(principal.getName());
        model.addAttribute("user", userDto);

        model.addAttribute("addNewCreditCard", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        UserBilling userBilling = new UserBilling();
        UserPayment userPayment = new UserPayment();


        model.addAttribute("userBilling", userBilling);
        model.addAttribute("userPayment", userPayment);

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);
        model.addAttribute("userPaymentList", userDto.getUserPaymentDtos());
        model.addAttribute("userShippingList", userDto.getUserShippingDtos());
		*//*model.addAttribute("orderList", user.orderList());*//*

        return "user/form/myProfile";
    }

    @RequestMapping("/addNewShippingAddress")
    public String addNewShippingAddress(
            Model model, Principal principal
    ){
        UserDto userDto = userService.findByUsername(principal.getName());
        model.addAttribute("user", userDto);

        model.addAttribute("addNewShippingAddress", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfCreditCards", true);

        UserShipping userShipping = new UserShipping();

        model.addAttribute("userShipping", userShipping);

        List<String> stateList = USConstants.listOfUSStatesCode;
        Collections.sort(stateList);
        model.addAttribute("stateList", stateList);
        model.addAttribute("userPaymentList", user.getUserPayments());
        model.addAttribute("userShippingList", user.getUserShippings());
		*//*model.addAttribute("orderList", user.orderList());*//*

        return "user/form/myProfile";
    }

    @RequestMapping(value="/addNewCreditCard", method= RequestMethod.POST)
    public String addNewCreditCard(
            @ModelAttribute("userPayment") UserPayment userPayment,
            @ModelAttribute("userBilling") UserBilling userBilling,
            Principal principal, Model model
    ){
        User user = userService.findByUsernameU(principal.getName());
        myProfileService.updateUserBilling(userBilling, userPayment, user);

        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPayments());
        model.addAttribute("userShippingList", user.getUserShippings());
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        return "user/form/myProfile";
    }

    @RequestMapping(value="/addNewShippingAddress", method=RequestMethod.POST)
    public String addNewShippingAddressPost(
            @ModelAttribute("userShipping") UserShipping userShipping,
            Principal principal, Model model
    ){
        User user = userService.findByUsernameU(principal.getName());
      myProfileService.updateUserShipping(userShipping, user);

        model.addAttribute("user", user);
        model.addAttribute("userPaymentList", user.getUserPayments());
        model.addAttribute("userShippingList", user.getUserShippings());
        model.addAttribute("listOfShippingAddresses", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfCreditCards", true);

        return "user/form/myProfile";
    }


    @RequestMapping("/updateCreditCard")
    public String updateCreditCard(
            @ModelAttribute("id") Long creditCardId, Principal principal, Model model
    ) {

        *//*userPaymentService.update(id,principal);*//*
        User user = userService.findByUsernameU(principal.getName());
        UserPayment userPayment = userPaymentService.findById(creditCardId);

        if(user.getId() != userPayment.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);
            UserBilling userBilling = userPayment.getUserBilling();
            model.addAttribute("userPayment", userPayment);
            model.addAttribute("userBilling", userBilling);

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            model.addAttribute("addNewCreditCard", true);
            model.addAttribute("classActiveBilling", true);
            model.addAttribute("listOfShippingAddresses", true);

            model.addAttribute("userPaymentList", user.getUserPayments());
            model.addAttribute("userShippingList", user.getUserShippings());

            return "user/form/myProfile";
        }
    }

    @RequestMapping("/updateUserShipping")
    public String updateUserShipping(
            @ModelAttribute("id") Long shippingAddressId, Principal principal, Model model
    ) {
        User user = userService.findByUsernameU(principal.getName());
        UserShipping userShipping = userShippingService.findById(shippingAddressId);

        if(user.getId() != userShipping.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);

            model.addAttribute("userShipping", userShipping);

            List<String> stateList = USConstants.listOfUSStatesCode;
            Collections.sort(stateList);
            model.addAttribute("stateList", stateList);

            model.addAttribute("addNewShippingAddress", true);
            model.addAttribute("classActiveShipping", true);
            model.addAttribute("listOfCreditCards", true);

            model.addAttribute("userPaymentList", user.getUserPayments());
            model.addAttribute("userShippingList", user.getUserShippings());

            return "user/form/myProfile";
        }
    }

    @RequestMapping(value="/setDefaultPayment", method=RequestMethod.POST)
    public String setDefaultPayment(
            @ModelAttribute("defaultUserPaymentId") Long defaultPaymentId, Principal principal, Model model
    ) {
        User user = userService.findByUsernameU(principal.getName());
        myProfileService.setUserDefaultPayment(defaultPaymentId, user);

        model.addAttribute("user", user);
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveBilling", true);
        model.addAttribute("listOfShippingAddresses", true);

        model.addAttribute("userPaymentList", user.getUserPayments());
        model.addAttribute("userShippingList", user.getUserShippings());

        return "user/form/myProfile";
    }

    @RequestMapping(value="/setDefaultShippingAddress", method=RequestMethod.POST)
    public String setDefaultShippingAddress(
            @ModelAttribute("defaultShippingAddressId") Long defaultShippingId, Principal principal, Model model
    ) {
        User user = userService.findByUsernameU(principal.getName());
        myProfileService.setUserDefaultShipping(defaultShippingId, user);

        model.addAttribute("user", user);
        model.addAttribute("listOfCreditCards", true);
        model.addAttribute("classActiveShipping", true);
        model.addAttribute("listOfShippingAddresses", true);

        model.addAttribute("userPaymentList", user.getUserPayments());
        model.addAttribute("userShippingList", user.getUserShippings());

        return "user/form/myProfile";
    }

    @RequestMapping("/removeCreditCard")
    public String removeCreditCard(
            @ModelAttribute("id") Long id, Principal principal, Model model
    ) throws Exception {
        UserDto userDto  = userService.findByUsername(principal.getName());
        userPaymentService.removeCreditCard(id,principal);


            model.addAttribute("listOfCreditCards", true);
            model.addAttribute("classActiveBilling", true);
            model.addAttribute("listOfShippingAddresses", true);

            model.addAttribute("userPaymentList", userDto.getUserPayments());
            model.addAttribute("userShippingList", userDto.getUserShippings());

            return "user/form/myProfile";
        }


    @RequestMapping("/removeUserShipping")
    public String removeUserShipping(
            @ModelAttribute("id") Long userShippingId, Principal principal, Model model
    ){
        User user = userService.findByUsernameU(principal.getName());
        UserShipping userShipping = userShippingService.findById(userShippingId);

        if(user.getId() != userShipping.getUser().getId()) {
            return "badRequestPage";
        } else {
            model.addAttribute("user", user);

            userShippingService.removeById(userShippingId);

            model.addAttribute("listOfShippingAddresses", true);
            model.addAttribute("classActiveShipping", true);

            model.addAttribute("userPaymentList", user.getUserPayments());
            model.addAttribute("userShippingList", user.getUserShippings());

            return "user/form/myProfile";
        }
    }
*/
}
