package com.purpleprint.network.purpleprintproject.character.command.domain.service;

import com.purpleprint.network.purpleprintproject.character.command.application.dto.PictureDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <pre>
 * Class : RecommendService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-09       이상학           최초 생성
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface RecommendService {


    ResponseEntity<String> recommendCharacter(PictureDTO pictureDTO) throws IOException;
}