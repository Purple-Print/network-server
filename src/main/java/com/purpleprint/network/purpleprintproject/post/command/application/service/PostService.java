package com.purpleprint.network.purpleprintproject.post.command.application.service;

import com.purpleprint.network.purpleprintproject.common.dto.ChildDTO;
import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import com.purpleprint.network.purpleprintproject.play.command.domain.model.Video;
import com.purpleprint.network.purpleprintproject.post.command.application.dto.JudgeDTO;
import com.purpleprint.network.purpleprintproject.post.command.application.exception.DeletePostFailException;
import com.purpleprint.network.purpleprintproject.post.command.application.exception.PostJudgeFailException;
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

    @Transactional
    public Post insertPost(ChildDTO child, Integer videoId) {
        Video savedVideo = postingVideoService.selectVideoByChildId(child.getChildId(), videoId);

        if(savedVideo == null) {
            throw new SavePostAndVideoFailException("게시할 영상이 존재하지 않습니다.");
        }

        Post savePost = null;
        try{
            savePost = postRepository.save(new Post(
                    0,
                    new Date(new Date().getTime()),
                    null,
                    null,
                    "N",
                    null,
                    savedVideo
            ));
        } catch (Exception e) {
            throw new SavePostAndVideoFailException("영상 게시 신청에 실패하셨습니다.");
        }

        return savePost;
    }
    public List<Post> selectPostApplicationList(UserDTO userDTO) {

        if(userDTO.getRole() == "ROLE_USER") {
            return postRepository.findAllByVideoChildId(userDTO.getChild().getChildId());
        }

        return postRepository.findAll();
    }

    @Transactional
    public Post updatePost(JudgeDTO judgeDTO) {

        Post post = postRepository.findByIdAndApproval(judgeDTO.getPostId(), null);
        if(post == null) {
            throw new PostJudgeFailException("이미 심사했거나 존재하지 않는 게시물입니다.");
        }

        try{
            post.setApproval(judgeDTO.getApproval());
            post.setCompanionReason(judgeDTO.getCompanionReason());
            post.setJudgeAt(new Date(new Date().getTime()));
        } catch (Exception e) {
            throw new PostJudgeFailException("심사에 실패하셨습니다.");
        }

        return post;
    }

    @Transactional
    public void deletePost(ChildDTO child, int postId) {
        Post deletePost = postRepository.findByIdAndVideoChildIdAndDeleteYn(postId, child.getChildId(), "N");

        if(deletePost == null) {
            throw new DeletePostFailException("게시물이 존재하지 않습니다.");
        }

        try {
            deletePost.setDeleteYn("Y");

        } catch(Exception e) {
            throw new DeletePostFailException("게시 영상 삭제에 실패하셨습니다.");
        }

    }
}
