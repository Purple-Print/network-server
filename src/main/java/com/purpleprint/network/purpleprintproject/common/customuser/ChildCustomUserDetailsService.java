package com.purpleprint.network.purpleprintproject.common.customuser;

import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.User;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.ChildRepository;
import com.purpleprint.network.purpleprintproject.auth.command.domain.repository.UserRepository;
import com.purpleprint.network.purpleprintproject.common.dto.ChildDTO;
import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <pre>
 * Class : ChildCustomUserDetailsService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-06       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Service
public class ChildCustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ChildRepository childRepository;

    @Autowired
    public ChildCustomUserDetailsService(UserRepository userRepository, ChildRepository childRepository) {
        this.userRepository = userRepository;
        this.childRepository = childRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Child child = childRepository.findByName(username);

        User user = userRepository.findById(child.getUserId()).get();

        ChildDTO childDTO = new ChildDTO();
        childDTO.setChildId(child.getId());
        childDTO.setChildName(child.getName());
        childDTO.setConnectNum(child.getConnectNum());
        childDTO.setGivenHeart(child.getGivenHeart());
        childDTO.setGrantHeart(child.getGrantHeart());

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPwd());
        userDTO.setRole(user.getRole().toString());
        userDTO.setEmail(user.getEmail());
        userDTO.setName(user.getName());
        userDTO.setChild(childDTO);
        userDTO.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDTO.getRole())));

        return userDTO;
    }

}
