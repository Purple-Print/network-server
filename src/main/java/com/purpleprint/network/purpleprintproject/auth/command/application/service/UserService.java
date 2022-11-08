package com.purpleprint.network.purpleprintproject.auth.command.application.service;

/**
 * <pre>
 * Class : UserService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       전현정           최초 생성
 * 2022-11-06       이상학           updateChild 메소드 추가
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

import com.purpleprint.network.purpleprintproject.auth.command.application.dto.*;
import com.purpleprint.network.purpleprintproject.auth.command.application.exception.*;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Login;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Logout;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.User;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.ChildRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.LoginRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.LogoutRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.UserRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.service.OwnerService;
import com.purpleprint.network.purpleprintproject.character.command.domain.model.Character;
import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import com.purpleprint.network.purpleprintproject.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final ChildRepository childRepository;
    private final LoginRepository loginRepository;
    private final LogoutRepository logoutRepository;
    private final TokenProvider tokenProvider;
    private final OwnerService ownerService;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    public UserService(UserRepository userRepository, ChildRepository childRepository, LoginRepository loginRepository,
                       TokenProvider tokenProvider, OwnerService ownerService, LogoutRepository logoutRepository, PasswordEncoder passwordEncoder) {

        this.userRepository = userRepository;
        this.childRepository= childRepository;
        this.loginRepository = loginRepository;
        this.logoutRepository = logoutRepository;
        this.tokenProvider = tokenProvider;
        this.ownerService = ownerService;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Child childAccountCreation(ChildDTO childDTO) {

        Optional<User> user = userRepository.findById(childDTO.getUserId());

        int result = childRepository.countByUserId(childDTO.getUserId());

        System.out.println(result);

        if(result >= 2) {
            throw new ChildAccountCreationFailException("계정 생성에 실패하셨습니다.");
        }

        Child child = new Child();

        child.setName(childDTO.getChildName());
        child.setUserId(user.get().getId());

        Child newChild = null;

        try {
           newChild = childRepository.save(child);

        } catch(Exception e) {

            throw new ChildAccountCreationFailException("계정 생성에 실패하셨습니다.");

        }

        return newChild;
    }

    @Transactional
    public void initializeGrantHeart() {

        List<Child> childList = childRepository.findAll();

        childList.forEach(child -> {
            child.setGrantHeart(5);
        });

    }

    @Transactional
    public void initializeGivenHeart() {

        List<Child> childList = childRepository.findAll();

        childList.forEach(child -> {
            child.setGivenHeart(0);
        });
    }

    public List<Child> selectChildList(int id) {

        return childRepository.findAllByUserId(id);
    }

    public int updateChild(int id) {

        Child myChild = childRepository.findById(id).get();

        if(myChild != null) {

            if(myChild.getGrantHeart() == 0) {
                throw new GrantFailException("하트 개수가 0입니다!");
            }

            myChild.setGivenHeart(myChild.getGivenHeart() + 1);
            myChild.setGrantHeart(myChild.getGrantHeart() - 1);

            return 1;

        } else {

            return 0;
        }

    }

    @Transactional
    public Boolean deleteUser(int id){

        try {
            User user = userRepository.findById(id).get();
            user.setSecYn("Y");
            user.setSecAt( new Date(new java.util.Date().getTime()));
        } catch(Exception e) {
            throw new DeleteUserFailException("회원 탈퇴에 실패하셨습니다.");
        }

        return true;
    }

    @Transactional
    public ChildInfoDTO connectChild(UserDTO userDTO, ChildDTO childDTO) {
        System.out.println(childDTO);

        Child child = childRepository.findByIdAndUserId(childDTO.getChildId(), userDTO.getId());
        User user = userRepository.findById(child.getUserId()).get();

        if(child == null) {
            throw new ConnectFailException("자녀 계정이 존재하지 않습니다.");
        }

        Login login = loginRepository.findByChildIdOrderByIdDesc(childDTO.getChildId());

        if(login != null) {
            Logout logout = logoutRepository.findByLoginId(login.getId());

            if(logout == null) {
                throw new ConnectFailException("자녀 계정을 이미 사용 중입니다.");
            }

        }

        try {
            loginRepository.save(new Login(
                    0,
                    new Date(new java.util.Date().getTime()),
                    child.getId()
            ));
            child.setConnectNum(child.getConnectNum() + 1);
        } catch(Exception e) {
            throw new ConnectFailException("자녀 계정 접속에 실패하셨습니다.");
        }

        //토큰 발행
        TokenDTO tokenDTO = tokenProvider.childTokenDto(user, child);

        //유저 캐릭터 조회
        Character characterInfo = ownerService.selectChildCharacter(child);

        //responsedto

        ChildInfoDTO childInfo = new ChildInfoDTO(
                child.getId(),
                child.getName(),
                child.getConnectNum(),
                child.getGrantHeart(),
                child.getGivenHeart(),
                null,
                tokenDTO.getAccessToken()
        );

        if(characterInfo != null) {
            CharacterDTO characterDTO = new CharacterDTO();
            characterDTO.setCharacterId(characterInfo.getId());
            characterDTO.setUrl(characterInfo.getCharacterFile().getUrl());
            characterDTO.setFileName(characterInfo.getCharacterFile().getName());
            childInfo.setCharacter(characterDTO);
        }


        return childInfo;
    }

    @Transactional
    public void updatePassword(UserDTO userDTO, PasswordDTO passwordDTO) {

        User user = userRepository.findById(userDTO.getId()).get();
        try {
            user.setPwd(passwordEncoder.encode(passwordDTO.getPassword()));
        } catch(Exception e) {
            throw new UpdatePasswordFailException("비밀번호 변경에 실패했습니다.");
        }
    }

}
