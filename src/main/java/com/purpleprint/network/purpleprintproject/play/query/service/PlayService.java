package com.purpleprint.network.purpleprintproject.play.query.service;

import com.purpleprint.network.purpleprintproject.play.command.domain.model.Video;
import com.purpleprint.network.purpleprintproject.play.command.domain.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : PlayService
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
@Service("query.PlayService")
public class PlayService {

    private final VideoRepository videoRepository;

    @Autowired
    public PlayService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video selectVideoByChildId(int childId, Integer videoId) {

        return videoRepository.findByIdAndChildIdAndDeleteYn(videoId, childId, "N");
    }
}
