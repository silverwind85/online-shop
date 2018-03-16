package com.shop.service.impl;


import com.shop.configuration.AdminConf;
import com.shop.domain.*;
import com.shop.domain.dto.UserDto;
import com.shop.domain.security.PasswordResetToken;
import com.shop.domain.security.Role;
import com.shop.domain.security.UserRole;
import com.shop.mapper.UserMapper;

import com.shop.repository.PasswordResetTokenRepository;
import com.shop.repository.RoleRepository;
import com.shop.repository.UserRepository;
import com.shop.service.SimpleEmailService;
import com.shop.service.UserService;
import com.shop.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    private UserRepository userRepository;


    private UserMapper userMapper;

    @Autowired
    private AdminConf adminConf;

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private UserSecurityService userSecurityService;


    @Autowired
    private JavaMailSender mailSender;


    @Override
    public PasswordResetToken getPasswordResetToken(final String token) {
        return passwordResetTokenRepository.findByToken(token);

    }

    @Override
    public void createPasswordResetTokenForUser(final User user, final String token) {
        final PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordResetTokenRepository.save(myToken);
    }

    @Override
    public UserDto findByUsername(final String username)  {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            LOGGER.info("Not found user");
        }
            return userMapper.INSTANCE.userToUserDto(user);
    }
    @Override
    public User findByUsernameU(String username) {
        return  userRepository.findByUsername(username);
    }

    @Override
    public User update(final UserDto userDto) {
        User user = userMapper.INSTANCE.userDtotoUser(userDto);
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(user.getNewPassword());
        user.setEnabled(true);
        user.setPassword(encryptedPassword);
        save(user);
        confirmUser(user);

        return user;
    }


    @Override
    public UserDto findByEmail(final String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {

        }
            return userMapper.INSTANCE.userToUserDto(userRepository.findByEmail(email));
    }


    public UserDto createUser(final String username, final String email, HttpServletRequest request) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        String password = SecurityUtility.randomPassword();

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);


        /*user.setEnabled(true);*/

        Set<UserRole> userRoles = createUserRoles(user, createRole("ROLE_USER"));


        for (UserRole ur : userRoles
                ) {
            roleRepository.save(ur.getRole());
        }
        user.getUserRoles().addAll(userRoles);
        User localUser = userRepository.save(user);


        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(localUser, token);

        simpleEmailService.sent(createMailRegister(localUser, request, token,password));

        return userMapper.INSTANCE.userToUserDto(localUser);
    }

    @Override
    public UserDto save(final User user) {
        return userMapper.INSTANCE.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto forgetPassword(final String email, final UserDto userDto, final HttpServletRequest request) {

        User user = userMapper.INSTANCE.userDtotoUser(userDto);
        String password = SecurityUtility.randomPassword();
        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);
        UserDto userDto1 = save(user);
        String token = UUID.randomUUID().toString();
        createPasswordResetTokenForUser(user, token);
        simpleEmailService.sent(createMailForgetPassword(user, request,password));

        return userDto1;
    }

    @Override
    public UserDto confirmUser(User user) {

        String username = user.getUsername();

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return userMapper.INSTANCE.userToUserDto(user);
    }
    




    private String  createPassword() {
        String password = SecurityUtility.randomPassword();
        return password;
    }
    private String encryptedPassword(String createPassword){
        return SecurityUtility.passwordEncoder().encode(createPassword());
    }

    private Role createRole(String roleName) {
        Role role = new Role();
        role.setId(1);
        role.setName(roleName);
        return role;
    }

    private Set<UserRole> createUserRoles(User user, Role role) {
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        return userRoles;

    }

    private Mail createMailForgetPassword(final User user, final HttpServletRequest request, final String password) {
        String appUrl = adminConf.getDomain() + request.getContextPath();
        String title = "Przypomnienie hasła";
        String message = "\n Kliknij na tem link, aby zresetować hasło. Twoje hasło: \n" + password;
        return new Mail(user.getEmail(), title, appUrl + message);
    }

    private Mail createMailRegister(final User user, final HttpServletRequest request, final String token, final String password) {
        String appUrl = adminConf.getDomain() + request.getContextPath() + "/confirm?token=" + token;
        String title = "Nowy Użytkownik";
        String message = "\n Kliknij na tem link, aby zweryfikować email. Twoje hasło: \n" + password;
        return new Mail(user.getEmail(), title, appUrl + message);
    }
}
