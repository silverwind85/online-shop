/*
package com.shop.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ExceptionController.class);
    @ExceptionHandler(value = Exception.class)
    public String handleException(HttpServletRequest request, Exception ex) {
        LOGGER.error("Request" + request.getRequestURL() + "Threw an Exception", ex);
        return "common/exception/error";
    }
}
*/
