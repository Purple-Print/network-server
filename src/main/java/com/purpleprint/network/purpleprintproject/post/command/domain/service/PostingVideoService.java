package com.purpleprint.network.purpleprintproject.post.command.domain.service;

import com.purpleprint.network.purpleprintproject.common.dto.ChildDTO;
import com.purpleprint.network.purpleprintproject.play.command.domain.model.Video;
import com.purpleprint.network.purpleprintproject.post.command.domain.model.Post;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <pre>
 * Class : PostingVideoService
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
public interface PostingVideoService {
    Video insertVideo(ChildDTO child, MultipartFile post);

    Video selectVideoByChildId(int childId, Integer videoId);
}
