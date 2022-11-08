package com.purpleprint.network.purpleprintproject.character.command.application.controller;

import com.purpleprint.network.purpleprintproject.character.command.application.dto.CharacterDTO;
import com.purpleprint.network.purpleprintproject.character.command.application.service.CharacterService;
import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 * Class : CharacterController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       이상학           최초 생성
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
@RestController
@RequestMapping("/character")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping("")
    public ResponseEntity<?> postCharacter(@AuthenticationPrincipal UserDTO userDTO, @RequestBody CharacterDTO characterDTO) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        boolean result = characterService.createCharacter(userDTO, characterDTO);

        if(result) {
            return ResponseEntity
                    .ok()
                    .body(new ResponseMessage(HttpStatus.OK, "캐릭터 생성 성공", responseMap));
        } else{
            return ResponseEntity
                    .badRequest()
                    .body(new ResponseMessage(HttpStatus.BAD_REQUEST, "캐릭터 생성 실패", responseMap));
        }
    }
}
