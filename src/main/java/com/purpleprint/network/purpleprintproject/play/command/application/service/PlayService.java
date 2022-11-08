package com.purpleprint.network.purpleprintproject.play.command.application.service;

import com.purpleprint.network.purpleprintproject.common.dto.ChildDTO;
import com.purpleprint.network.purpleprintproject.play.command.application.dto.VideoDTO;
import com.purpleprint.network.purpleprintproject.play.command.application.exception.SaveVideoFailedException;
import com.purpleprint.network.purpleprintproject.play.command.domain.model.Video;
import com.purpleprint.network.purpleprintproject.play.command.domain.repository.VideoRepository;
import com.purpleprint.network.purpleprintproject.s3.dto.FileDTO;
import com.purpleprint.network.purpleprintproject.s3.service.AwsS3UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Class : PlayService
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
@Service
public class PlayService {

    private final AwsS3UploadService awsS3UploadService;
    private final VideoRepository videoRepository;

    @Autowired
    public PlayService(AwsS3UploadService awsS3UploadService, VideoRepository videoRepository) {
        this.awsS3UploadService = awsS3UploadService;
        this.videoRepository = videoRepository;
    }

    @Transactional
    public Video saveVideo(ChildDTO child, VideoDTO videoDTO) {

        Video saveVideo = null;

        try {
            FileDTO saveFileInfo = awsS3UploadService.upload("video", videoDTO.getVideo());

            saveVideo = videoRepository.save(new Video(
                    0,
                    saveFileInfo.getOriginalFileName(),
                    saveFileInfo.getConversionFileName(),
                    saveFileInfo.getSaveUrl(),
                    saveFileInfo.getS3Key(),
                    new Date(new java.util.Date().getTime()),
                    "N",
                    child.getChildId()
            ));

        } catch(Exception e) {
            throw new SaveVideoFailedException("비디오 저장에 실패하셨습니다.");
        }

        return saveVideo;
    }

    public List<Video> selectVideoByChildId(int childId) {

        return videoRepository.findAllByChildIdAndDeleteYn(childId, "N");
    }
}
