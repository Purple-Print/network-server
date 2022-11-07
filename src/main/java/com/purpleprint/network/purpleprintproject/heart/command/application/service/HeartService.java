package com.purpleprint.network.purpleprintproject.heart.command.application.service;

import com.purpleprint.network.purpleprintproject.auth.command.application.exception.GrantFailException;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.heart.command.application.dto.HeartDTO;
import com.purpleprint.network.purpleprintproject.heart.command.domain.model.Heart;
import com.purpleprint.network.purpleprintproject.heart.command.domain.repository.HeartRepository;
import com.purpleprint.network.purpleprintproject.heart.command.domain.service.ChildHeartService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
    public List<Integer> giveHeart(HeartDTO heartDTO) throws ParseException {

        String t1 = LocalDate.now() + " 00:00:00";
        String t2 = LocalDate.now() + " 23:59:59";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date1 = formatter.parse(t1);
        Date date2 = formatter.parse(t2);

        List<Heart> heartInfo = heartRepository.findByGiverAndGaveAtBetween(heartDTO.getGiver(), date1, date2);

        System.out.println("heartInfo : " + heartInfo);

        for(int i=0; i<heartInfo.size(); i++){
            if(heartInfo.get(i).getRecipient() == heartDTO.getRecipient()) {
                throw new GrantFailException("하트 나눔 실패!");
            }
        }

        Heart newHeart =  heartRepository.save(new Heart(
                    0,
                    heartDTO.getGiver(),
                    heartDTO.getRecipient(),
                    new Date(new java.util.Date().getTime())
            ));

        List<Integer> recipientInfo = heartRepository.findAllRecipient(heartDTO.getGiver(), date1, date2);

        int giveHeartResult = childHeartService.giveHeart(heartDTO.getGiver());

        if(giveHeartResult == 0) {

            throw new GrantFailException("하트 나눔 실패!");
        } else{

            return recipientInfo;
        }
    }

}
