package com.purpleprint.network.purpleprintproject.auth.query.controller;


import com.purpleprint.network.purpleprintproject.auth.query.dto.UsernameDTO;
import com.purpleprint.network.purpleprintproject.auth.query.service.AuthService;
import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Class : AuthController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

@RestController("query.AuthController")
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/id-check")
    public ResponseEntity<?> dupCheckId (@RequestBody UsernameDTO usernameDTO) {

        HttpHeaders headers = new HttpHeaders(); //헤더 생성
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8"))); //header contentType 설정
        Map<String,Object> responseMap = new HashMap<>();

        int result = authService.dupCheckId(usernameDTO.getUsername());
        responseMap.put("username", usernameDTO.getUsername());

        if (result != 0){
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST, "username is duplicated", responseMap));
        } else {
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(HttpStatus.OK, "username is available",responseMap));
        }
    }
}
