
package com.purpleprint.network.purpleprintproject.character.command.infra.service;

import com.purpleprint.network.purpleprintproject.character.command.application.dto.PictureDTO;
import com.purpleprint.network.purpleprintproject.character.command.application.dto.ResponseDTO;
import com.purpleprint.network.purpleprintproject.character.command.application.exception.RecommendCharacterFailException;
import com.purpleprint.network.purpleprintproject.character.command.domain.service.RecommendService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

/**
 * <pre>
 * Class : RecommendServiceImpl
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-09       이상학           최초 생성
 * 2022-11-21       전현정           캐릭터 추천 수정 exception 처리
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class RecommendServiceImpl implements RecommendService {

    @Override
    public String recommendCharacter(PictureDTO pictureDTO) throws IOException {

        if(pictureDTO.getImageFile().isEmpty()) {
            throw new RecommendCharacterFailException("파일이 존재하지 않습니다. 파일을 전송해주세요.");
        }

        System.out.println(pictureDTO.getImageFile().getOriginalFilename());
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

        String serverURL = "http://34.64.214.200:8000/facedata";

        ResponseEntity<ResponseDTO> response = null;

        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.postForEntity(serverURL, requestEntity, ResponseDTO.class);

            System.out.println("response : " + response.getBody());
        } catch (Exception e) {
            throw new RecommendCharacterFailException("파일 형식이 정확하지 않습니다. 파일을 다시 보내주세요.");
        }



        ResponseDTO responseCharacter = response.getBody();

        String skinColor = null;

        switch(responseCharacter.getSkinColor()) {
            case "color1" : skinColor = "Redish"; break;
            case "color2" : skinColor = "White"; break;
            case "color3" : skinColor = "Yellowish"; break;
            case "color4" : skinColor = "Black"; break;
        }

        String characterFileName = responseCharacter.getFaceShape() + "_" + skinColor + "_Skin";

        return characterFileName;
    }
}