package com.shop.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class AdminConf {

    @Value("${admin.mail}")
    private String adminMail;

    @Value("${domain}")
    private String domain;
}
