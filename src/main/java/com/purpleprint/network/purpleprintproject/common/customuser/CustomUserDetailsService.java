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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <pre>
 * Class : CustomUserDetailsService
 * Comment:
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-02       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ChildRepository childRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository, ChildRepository childRepository) {
        this.userRepository = userRepository;
        this.childRepository = childRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if(user == null){
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다.");
        }

        /* custom user 정보 생성 */
        List<Child> childList = childRepository.findAllByUserId(user.getId());
        UserDTO userDto = new UserDTO();
        List<ChildDTO> childDTOList = new ArrayList<>();
        for (Child child : childList) {
            ChildDTO childDto = new ChildDTO();
            childDto.setChildId(child.getId());
            childDto.setConnectNum(child.getConnectNum());
            childDto.setChildName(child.getName());
            childDto.setGrantHeart(child.getGrantHeart());
            childDto.setGivenHeart(child.getGivenHeart());

            childDTOList.add(childDto);
        }

        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPwd());
        userDto.setRole(user.getRole().toString());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());
        userDto.setChildList(childDTOList);
        userDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(userDto.getRole())));

        return userDto;
    }

}
