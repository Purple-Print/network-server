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
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

import com.purpleprint.network.purpleprintproject.auth.command.application.dto.ChildDTO;
import com.purpleprint.network.purpleprintproject.auth.command.application.exception.ChildAccountCreationFailException;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.User;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.ChildRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.LoginRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final ChildRepository childRepository;
    private final LoginRepository loginRepository;

    @Autowired
    public UserService(UserRepository userRepository, ChildRepository childRepository, LoginRepository loginRepository) {
        this.userRepository = userRepository;
        this.childRepository= childRepository;
        this.loginRepository = loginRepository;
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

    public List<Child> selectChildList(int id) {

        return childRepository.findAllByUserId(id);
    }
}
