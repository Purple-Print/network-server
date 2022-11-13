
package com.purpleprint.network.purpleprintproject.character.command.infra.service;

import com.purpleprint.network.purpleprintproject.character.command.application.dto.PictureDTO;
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
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class RecommendServiceImpl implements RecommendService {


    @Override
    public ResponseEntity<String> recommendCharacter(PictureDTO pictureDTO) throws IOException {

        String serverURL = "https://55db-119-194-163-123.jp.ngrok.io/files";

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


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(serverURL, requestEntity, String.class);

        System.out.println("response : " + response.getBody());

        return response;
    }
}