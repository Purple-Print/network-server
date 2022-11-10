package com.purpleprint.network.purpleprintproject.post.command.application.service;

import com.purpleprint.network.purpleprintproject.common.dto.ChildDTO;
import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import com.purpleprint.network.purpleprintproject.play.command.domain.model.Video;
import com.purpleprint.network.purpleprintproject.post.command.application.exception.SavePostAndVideoFailException;
import com.purpleprint.network.purpleprintproject.post.command.domain.model.Post;
import com.purpleprint.network.purpleprintproject.post.command.domain.repository.PostRepository;
import com.purpleprint.network.purpleprintproject.post.command.domain.service.PostingVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Class : PostService
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

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostingVideoService postingVideoService;


    @Autowired
    public PostService(PostRepository postRepository, PostingVideoService postingVideoService) {
        this.postRepository = postRepository;
        this.postingVideoService = postingVideoService;
    }
    public List<Post> selectPostList() {

        return postRepository.findAllByApprovalAndDeleteYn("Y", "N");
    }
    @Transactional
    public Post insertPostAndVideo(ChildDTO child, MultipartFile post) {
        Video saveVideo = postingVideoService.insertVideo(child, post);

        Post savePost = null;

        try {
            savePost = postRepository.save(new Post(
                    0,
                    new Date(new Date().getTime()),
                    null,
                    null,
                    "N",
                    null,
                    saveVideo
            ));
        } catch (Exception e) {
            throw new SavePostAndVideoFailException("영상 게시 신청에 실패하셨습니다.");
        }

        return savePost;
    }


}
