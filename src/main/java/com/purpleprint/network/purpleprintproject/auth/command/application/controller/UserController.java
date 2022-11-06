package com.purpleprint.network.purpleprintproject.auth.command.application.controller;

import com.purpleprint.network.purpleprintproject.auth.command.application.dto.ChildDTO;
import com.purpleprint.network.purpleprintproject.auth.command.application.dto.ChildInfoDTO;
import com.purpleprint.network.purpleprintproject.auth.command.application.dto.PasswordDTO;
import com.purpleprint.network.purpleprintproject.auth.command.application.service.UserService;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : UserController
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

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/child")
    public ResponseEntity<?> postChild(@AuthenticationPrincipal UserDTO userDTO, @Valid @RequestBody ChildDTO childDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        childDTO.setUserId(userDTO.getId());

        Child child = userService.childAccountCreation(childDTO);

        List<Child> childList = userService.selectChildList(userDTO.getId());

        responseMap.put("childInfo", childList);

        return ResponseEntity
                .created(URI.create("/user/"+child.getId()))
                .body(new ResponseMessage(HttpStatus.CREATED, "child account create success", responseMap));
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteUser(@AuthenticationPrincipal UserDTO userDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        boolean result = userService.deleteUser(userDTO.getId());

        if(result) {
            responseMap.put("status", "success");
        }

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(HttpStatus.OK, "User secession Success", responseMap));

    }

    @PostMapping("/child/connect")
    public ResponseEntity<?> postConnectChild(@AuthenticationPrincipal UserDTO userDTO, @Valid @RequestBody ChildDTO childDTO) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        ChildInfoDTO childInfo = userService.connectChild(userDTO, childDTO);

        responseMap.put("childInfo", childInfo);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(HttpStatus.OK, "Child Login Success", responseMap));

    }

    @PutMapping("")
    public ResponseEntity<?> putUserInfo(@AuthenticationPrincipal UserDTO userDTO, @Valid @RequestBody PasswordDTO passwordDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String,Object> responseMap = new HashMap<>();

        userService.updatePassword(userDTO, passwordDTO);

        responseMap.put("message", "비밀번호 변경에 성공하셨습니다.");

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(HttpStatus.OK, "edit password success ", responseMap));

    }
}
