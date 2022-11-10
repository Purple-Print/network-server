package com.purpleprint.network.purpleprintproject.post.command.application.controller;

import com.amazonaws.Response;
import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import com.purpleprint.network.purpleprintproject.common.responsemessage.ResponseMessage;
import com.purpleprint.network.purpleprintproject.post.command.application.dto.PostDTO;
import com.purpleprint.network.purpleprintproject.post.command.application.service.PostService;
import com.purpleprint.network.purpleprintproject.post.command.domain.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
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
 * Class : PostController
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/video")
    public ResponseEntity<?> getPostedVideos() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        List<Post> postList = postService.selectPostList();

        responseMap.put("postList", postList);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(HttpStatus.OK,"select videoList success",responseMap));
    }
    @PostMapping("")
    public ResponseEntity<?> postPosting(@AuthenticationPrincipal UserDTO userDTO, PostDTO postDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        Post postApplication = null;

        if(postDTO.getVideoId() != null) {
            postApplication = postService.insertPost(userDTO.getChild(), postDTO.getVideoId());
        } else {
            postApplication = postService.insertPostAndVideo(userDTO.getChild(), postDTO.getFile());
        }

        responseMap.put("posting", postApplication);

        return ResponseEntity
                .created(URI.create("/post"))
                .body(new ResponseMessage(HttpStatus.CREATED, "post application success", responseMap));
    }

    @GetMapping("")
    public ResponseEntity<?> getRequestedList(@AuthenticationPrincipal UserDTO userDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Map<String, Object> responseMap = new HashMap<>();

        List<Post> postApplicationList = postService.selectPostApplicationList(userDTO);
        responseMap.put("postApplicationList", postApplicationList);

        return ResponseEntity
                .ok()
                .body(new ResponseMessage(HttpStatus.OK, "post application select success", responseMap));
    }

}
