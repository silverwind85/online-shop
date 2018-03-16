package com.shop;


import com.shop.domain.User;
import com.shop.domain.security.Role;
import com.shop.domain.security.UserRole;
import com.shop.service.UserService;
import com.shop.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class ShopApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ShopApplication.class, args);
	}
	@Override
	public void run(String... strings) throws Exception {
		/*User user1 = new User();
		user1.setFirstName("Sławek");
		user1.setLastName("Cieslar");
		user1.setUsername("Admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("mlcieslar@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1,role1));

		userService.save(user1);*/
	}
}
