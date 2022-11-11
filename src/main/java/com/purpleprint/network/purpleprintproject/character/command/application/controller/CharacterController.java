package com.purpleprint.network.purpleprintproject.character.command.application.controller;

import com.purpleprint.network.purpleprintproject.character.command.application.dto.CharacterDTO;
import com.purpleprint.network.purpleprintproject.character.command.application.dto.PictureDTO;
import com.purpleprint.network.purpleprintproject.character.command.application.exception.PictureReceiveFailException;
import com.purpleprint.network.purpleprintproject.character.command.application.service.CharacterService;
import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.print.attribute.standard.Media;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

    private static byte[] getFileBinary(String filepath) {
        File file = new File(filepath);
        byte[] data = new byte[(int) file.length()];
        try (FileInputStream stream = new FileInputStream(file)) {
            stream.read(data, 0, data.length);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return data;
    }
    @PostMapping("/recommend")
    public ResponseEntity<?> recommendCharacter(@AuthenticationPrincipal UserDTO userDTO, PictureDTO pictureDTO) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        byte[] imageData = pictureDTO.getImageFile().getBytes();

        ByteArrayResource imageResource = new ByteArrayResource(imageData) {
            @Override
            public String getFilename() {
                return pictureDTO.getImageFile().getOriginalFilename();
            }
        };

        System.out.println("imageData : " + imageData);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", imageResource);

        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);

        String serverURL = "https://55db-119-194-163-123.jp.ngrok.io/files";


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(serverURL, requestEntity, String.class);

        System.out.println("response : " + response.getBody());
//
//        return response;
//        String recommendInfo = characterService.recommendCharacter(userDTO, pictureDTO.getImageFile());
//
        Map<String, Object> responseMap = new HashMap<>();

        responseMap.put("recommendInfo"," recommendInfo");

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(HttpStatus.OK, "사진 업로드 성공", responseMap));

    }

}
