package com.purpleprint.network.purpleprintproject.post.command.infra.service;

import com.purpleprint.network.purpleprintproject.common.dto.ChildDTO;
import com.purpleprint.network.purpleprintproject.play.command.application.service.PlayService;
import com.purpleprint.network.purpleprintproject.play.command.domain.model.Video;
import com.purpleprint.network.purpleprintproject.post.command.domain.model.Post;
import com.purpleprint.network.purpleprintproject.post.command.domain.service.PostingVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <pre>
 * Class : PostingVideoServiceImpl
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
public class PostingVideoServiceImpl implements PostingVideoService {

    private final PlayService playService;
    private final com.purpleprint.network.purpleprintproject.play.query.service.PlayService queryPlayService;

    @Autowired
    public PostingVideoServiceImpl(PlayService playService, com.purpleprint.network.purpleprintproject.play.query.service.PlayService queryPlayService) {
        this.playService = playService;
        this.queryPlayService = queryPlayService;
    }
    @Override
    public Video insertVideo(ChildDTO child, MultipartFile post) {
        return playService.saveVideo(child, post);
    }

    @Override
    public Video selectVideoByChildId(int childId, Integer videoId) {
        return queryPlayService.selectVideoByChildId(childId, videoId);
    }
}
