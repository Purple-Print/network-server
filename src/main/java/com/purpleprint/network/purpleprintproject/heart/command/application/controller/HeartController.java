package com.purpleprint.network.purpleprintproject.heart.command.application.controller;

import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;
import com.purpleprint.network.purpleprintproject.heart.command.application.dto.HeartDTO;
import com.purpleprint.network.purpleprintproject.heart.command.application.service.HeartService;
import org.hibernate.sql.OracleJoinFragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : HeartController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       이상학           최초 생성
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
@RestController
@RequestMapping("/heart")
public class HeartController {

    private final HeartService heartService;

    @Autowired
    public HeartController(HeartService heartService) {
        this.heartService = heartService;
    }

    @PostMapping("")
    public ResponseEntity<?> giveHeart (@RequestBody HeartDTO heartDTO) throws ParseException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        List<Integer> result = heartService.giveHeart(heartDTO);

        responseMap.put("recipient ", result);
        return ResponseEntity
                .ok()
                .body(new ResponseMessage(HttpStatus.OK, "하트 나눠주기 성공!", responseMap));
    }
}
