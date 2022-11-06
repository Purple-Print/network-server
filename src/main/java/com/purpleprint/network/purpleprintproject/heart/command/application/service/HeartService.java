package com.purpleprint.network.purpleprintproject.heart.command.application.service;

import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.heart.command.application.dto.HeartDTO;
import com.purpleprint.network.purpleprintproject.heart.command.domain.model.Heart;
import com.purpleprint.network.purpleprintproject.heart.command.domain.repository.HeartRepository;
import com.purpleprint.network.purpleprintproject.heart.command.domain.service.ChildHeartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Class : HeartService
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

@Service
public class HeartService {

    private final HeartRepository heartRepository;
    private final ChildHeartService childHeartService;

    public HeartService(HeartRepository heartRepository, ChildHeartService childHeartService) {
        this.heartRepository = heartRepository;
        this.childHeartService = childHeartService;
    }

    @Transactional
    public int giveHeart(HeartDTO heartDTO) {

        List<Heart> heartInfo = heartRepository.findByGiverAndGaveAt(heartDTO.getGiver(), new Date());

        for(int i=0; i<heartInfo.size(); i++){
            if(heartInfo.get(i).getId() == heartDTO.getId()) {
                return 0;
            }
        }

        Heart newHeart =  heartRepository.save(new Heart(
                    0,
                    heartDTO.getGiver(),
                    heartDTO.getRecipient(),
                    new Date(new java.util.Date().getTime())
            ));

        int giveHeartResult = childHeartService.giveHeart(heartDTO.getId());

        if(giveHeartResult == 0) {

            return 0;
        } else{

            return 1;
        }

    }
}
