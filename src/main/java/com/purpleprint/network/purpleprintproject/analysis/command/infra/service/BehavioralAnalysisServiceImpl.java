package com.purpleprint.network.purpleprintproject.analysis.command.infra.service;

import com.purpleprint.network.purpleprintproject.analysis.command.domain.model.AbnormalBehavior;
import com.purpleprint.network.purpleprintproject.analysis.command.domain.model.Log;
import com.purpleprint.network.purpleprintproject.analysis.command.domain.service.BehavioralAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * <pre>
 * Class : BehavioralAnalysisServiceImpl
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-09       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Service
public class BehavioralAnalysisServiceImpl implements BehavioralAnalysisService {

    @Async
    @Override
    public void analysisBehavior(AbnormalBehavior abnormalBehavior, List<Log> savedLog) {

        System.out.println(abnormalBehavior);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        String imageData = "test";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("abnormalBehavior", abnormalBehavior.toString());
        System.out.println(body);

        HttpEntity<?> requestEntity = new HttpEntity<>(body, headers);

        String serverURL = "http://192.168.0.184:8080/auth/test";

        AsyncRestTemplate restTemplate = new AsyncRestTemplate();
        ListenableFuture<ResponseEntity<String>> response = restTemplate.postForEntity(serverURL, requestEntity, String.class);

        try {
            System.out.println(response.get().getBody());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
