package com.shop.domain.dto;

import com.shop.domain.security.UserRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRoleDto {

    private int id;
    private String name;
    private Set<UserRole> userRoles;
}
