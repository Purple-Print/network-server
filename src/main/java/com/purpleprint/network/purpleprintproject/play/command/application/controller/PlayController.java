package com.purpleprint.network.purpleprintproject.play.command.application.controller;

import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;
import com.purpleprint.network.purpleprintproject.play.command.application.dto.VideoDTO;
import com.purpleprint.network.purpleprintproject.play.command.application.service.PlayService;
import com.purpleprint.network.purpleprintproject.play.command.domain.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class : PlayController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-08       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@RestController
@RequestMapping("/video")
public class PlayController {

    private final PlayService playService;

    @Autowired
    public PlayController(PlayService playService) {
        this.playService = playService;
    }

    @PostMapping("")
    public ResponseEntity<?> postVideo(@AuthenticationPrincipal UserDTO userDTO, VideoDTO videoDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application","json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Video video = playService.saveVideo(userDTO.getChild(), videoDTO);

        List<Video> videoList = playService.selectVideoByChildId(userDTO.getChild().getChildId());

        responseMap.put("videoList", videoList);

        return ResponseEntity
                .created(URI.create("video"))
                .body(new ResponseMessage(HttpStatus.OK, "video save success", responseMap));
    }
}