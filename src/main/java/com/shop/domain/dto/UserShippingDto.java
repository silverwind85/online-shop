package com.shop.domain.dto;

import com.shop.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserShippingDto {

    private Long id;
    private String userShippingName;
    private String userShippingStreet1;
    private String userShippingStreet2;
    private String userShippingCity;
    private String userShippingVoivodeship;
    private String userShippingCountry;
    private String userShippingZipcode;
    private boolean userShippingDefault;
    private User user;
}
