package com.purpleprint.network.purpleprintproject.auth.command.application.controller;

import com.purpleprint.network.purpleprintproject.auth.command.application.dto.LoginDTO;
import com.purpleprint.network.purpleprintproject.auth.command.application.dto.SignUpDTO;
import com.purpleprint.network.purpleprintproject.auth.command.application.dto.TokenDTO;
import com.purpleprint.network.purpleprintproject.auth.command.application.dto.UserDTO;
import com.purpleprint.network.purpleprintproject.auth.command.application.service.AuthService;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.User;
import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Class : AuthController
 * Comment: auth controller
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-01       전현정          최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup (@Valid @RequestBody SignUpDTO signUpDTO){

        HttpHeaders headers = new HttpHeaders(); //헤더 생성
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8"))); //header contentType 설정
        Map<String,Object> responseMap = new HashMap<>();

        UserDTO user = authService.signup(signUpDTO);
        responseMap.put("user", user);


        return ResponseEntity
                .created(URI.create("/user/"+signUpDTO.getUsername()))
                .body(new ResponseMessage(HttpStatus.CREATED, "SignUp Success", responseMap));
    }





}
