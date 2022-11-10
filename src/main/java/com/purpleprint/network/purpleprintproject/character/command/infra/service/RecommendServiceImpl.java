package com.purpleprint.network.purpleprintproject.character.command.infra.service;

import com.purpleprint.network.purpleprintproject.character.command.domain.service.RecommendService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

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

    private final String aiPath = "";
    @Override
    public String getRecommend(MultipartFile imageFile) {


//        String imageData = Base64.getEncoder().encodeToString(imageFile.getBytes());
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        String url = aiPath;
//
//        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
//        body.add("image", imageData);
//        HttpEntity<?> requestMessage = new HttpEntity<>(body, headers);
//        HttpEntity<String> aiResponse = restTemplate.postForEntity(url, requestMessage, String.class);
//        JSONParser parser = new JSONParser();
//
//        JSONObject data = (JSONObject) parser.parse(aiResponse.getBody());
//        String resultText = (String) data.get("text");
//        String resultImage = (String) data.get("image");
//        String resultByte = resultImage.replace("\n", "");
//
//        return recommendInfo;
        return "dd";
    }
}
