package com.purpleprint.network.purpleprintproject.auth.command.application.service;


import antlr.Token;
import com.purpleprint.network.purpleprintproject.auth.command.application.dto.*;
import com.purpleprint.network.purpleprintproject.auth.command.application.exception.LoginFailedException;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.User;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.UserRole;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.ChildRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.UserRepository;
import com.purpleprint.network.purpleprintproject.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <pre>
 * Class : AuthService
 * Comment: Auth Service
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-01       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final ChildRepository childRepository;
    private final MailService mailService;

    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthService(UserRepository userRepository, ChildRepository childRepository,
                       PasswordEncoder passwordEncoder, TokenProvider tokenProvider,
                       MailService mailService) {
        this.userRepository = userRepository;
        this.childRepository = childRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.mailService = mailService;
    }
    @Transactional
    public UserDTO signup(SignUpDTO signUpDTO) {

        User user = new User();
        user.setUsername(signUpDTO.getUsername());
        user.setPwd(passwordEncoder.encode(signUpDTO.getPassword()));
        user.setName(signUpDTO.getName());
        user.setEmail(signUpDTO.getEmail());
        user.setRole(UserRole.USER);

        User newUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(newUser.getId());
        userDTO.setUsername(newUser.getUsername());
        userDTO.setName(newUser.getName());
        userDTO.setEmail(newUser.getEmail());
        userDTO.setRole(newUser.getRole());

        return userDTO;
    }

    public int sendEmail(String email) throws MessagingException {

        int result = userRepository.countByEmail(email);

        System.out.println(email);

        if(result > 0) {
            return 0;
        }

        Random random = new Random();
        int certCode = random.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + certCode);

        mailService.sendEmail(email, certCode);

        return certCode;
    }

}
