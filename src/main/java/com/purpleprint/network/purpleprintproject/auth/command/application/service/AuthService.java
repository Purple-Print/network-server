package com.purpleprint.network.purpleprintproject.auth.command.application.service;


import antlr.Token;
import com.purpleprint.network.purpleprintproject.auth.command.application.dto.*;
import com.purpleprint.network.purpleprintproject.auth.command.application.exception.LoginFailedException;
import com.purpleprint.network.purpleprintproject.auth.command.application.exception.LogoutFailException;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.*;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.ChildRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.LoginRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.LogoutRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.UserRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.service.AwnerService;
import com.purpleprint.network.purpleprintproject.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.security.SecureRandom;
import java.util.*;

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
    private final LoginRepository loginRepository;
    private final LogoutRepository logoutRepository;
    private final MailService mailService;
    private final AwnerService awnerService;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;

    @Autowired
    public AuthService(UserRepository userRepository, ChildRepository childRepository,
                       LoginRepository loginRepository, LogoutRepository logoutRepository,
                       PasswordEncoder passwordEncoder, TokenProvider tokenProvider,
                       MailService mailService, AwnerService awnerService) {
        this.userRepository = userRepository;
        this.childRepository = childRepository;
        this.loginRepository = loginRepository;
        this.logoutRepository = logoutRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.mailService = mailService;
        this.awnerService = awnerService;
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
        User user = userRepository.findByUsernameAndSecYn(loginDTO.getUsername(), "N");

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

    public boolean matchPassword(MatchPasswordDTO matchPasswordDTO) throws MessagingException {

        User foundUser = userRepository.findByUsernameAndEmail(matchPasswordDTO.getUsername(), matchPasswordDTO.getEmail());

        if(foundUser == null) {
            return false;
        }

        String tempPassword = getRamdomPassword(10);
        System.out.println(tempPassword);

        String password = passwordEncoder.encode(tempPassword);

        foundUser.setPwd(password);
        foundUser.setTempPwd("Y");

        userRepository.save(foundUser);

        mailService.sendEmailForMatchPassword(foundUser.getEmail(), tempPassword);

        return true;
    }

    public String getRamdomPassword(int size) {
        char[] charSet = new char[] {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '!', '@', '#', '$', '%', '^', '&' };

        StringBuffer sb = new StringBuffer();
        SecureRandom sr = new SecureRandom();
        sr.setSeed(new Date().getTime());

        int idx = 0;
        int len = charSet.length;
        for (int i=0; i<size; i++) {
            // idx = (int) (len * Math.random());
            idx = sr.nextInt(len);    // 강력한 난수를 발생시키기 위해 SecureRandom을 사용한다.
            sb.append(charSet[idx]);
        }

        return sb.toString();
    }

    @Transactional
    public void logout(com.purpleprint.network.purpleprintproject.common.dto.ChildDTO child, LogoutDTO logoutDTO) {

        Login loginInfo = loginRepository.findTopByChildIdOrderByIdDesc(child.getChildId());

        System.out.println(child);

        Logout logout = logoutRepository.findByLoginId(loginInfo.getId());

        if(logout != null) {
            throw new LogoutFailException("이미 로그아웃 되셨습니다.");
        }

        System.out.println(loginInfo);

        try {

            logoutRepository.save(new Logout(
                    0,
                    logoutDTO.getXCoord(),
                    logoutDTO.getYCoord(),
                    logoutDTO.getZCoord(),
                    new Date(new java.util.Date().getTime()),
                    loginInfo.getId()
            ));
            awnerService.saveLogAndAbnormalBehavior(child.getChildId(), logoutDTO);

        } catch (Exception e) {
            throw new LogoutFailException("로그아웃 실패하셨습니다.");
        }

    }
}
