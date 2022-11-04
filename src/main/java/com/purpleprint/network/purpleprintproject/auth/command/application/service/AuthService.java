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
        user.setRole(UserRole.ROLE_USER);

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


    public UserDTO login(LoginDTO loginDTO) {
        //user 아이디 조회
        User user = userRepository.findByUsername(loginDTO.getUsername());

        if(user == null) {
            throw new LoginFailedException("존재하지 않는 아이디입니다.");
        }

        //password 매칭
        if(!passwordEncoder.matches(loginDTO.getPassword(), user.getPwd())) {
            throw new LoginFailedException("비밀번호가 일치하지 않습니다.");
        }

        //자녀 계정 정보 조회
        List<ChildDTO> childDTOList = new ArrayList<>();
        List<Child> childList = childRepository.findAllByUserId(user.getId());

        childList.forEach(child -> {
            ChildDTO childDTO = new ChildDTO();

            childDTO.setChildId(child.getId());
            childDTO.setChildName(child.getName());
            childDTO.setConnectNum(child.getConnectNum());
            childDTO.setGrantHeart(child.getGrantHeart());
            childDTO.setGivenHeart(child.getGivenHeart());

            childDTOList.add(childDTO);
        });

        // 3. 토큰 발급
        TokenDTO tokenDTO = tokenProvider.generateTokenDto(user);

        // 4. userDTO 생성
        UserDTO loginUser = new UserDTO();
        loginUser.setId(user.getId());
        loginUser.setUsername(user.getUsername());
        loginUser.setName(user.getName());
        loginUser.setEmail(user.getEmail());
        loginUser.setRole(user.getRole());
        loginUser.setAccessToken(tokenDTO.getAccessToken());
        loginUser.setChildList(childDTOList);

        return loginUser;
    }

    public Boolean matchUsername(MatchUsernameDTO matchUsernameDTO) throws MessagingException {

        User user = userRepository.findByNameAndEmail(matchUsernameDTO.getName(), matchUsernameDTO.getEmail());

        if(user == null) {
            return false;
        }

        mailService.sendEmailForMatchUsername(user.getEmail(), user.getUsername());

        return true;
    }
}
