package com.purpleprint.network.purpleprintproject.analysis.command.application.service;

import com.purpleprint.network.purpleprintproject.analysis.command.application.exception.SaveLogAndAbnormalBehaviorException;
import com.purpleprint.network.purpleprintproject.analysis.command.domain.model.AbnormalBehavior;
import com.purpleprint.network.purpleprintproject.analysis.command.domain.model.Log;
import com.purpleprint.network.purpleprintproject.analysis.command.domain.repository.AbnormalBehaviorRepoistory;
import com.purpleprint.network.purpleprintproject.analysis.command.domain.repository.LogRepository;
import com.purpleprint.network.purpleprintproject.analysis.command.domain.service.BehavioralAnalysisService;
import com.purpleprint.network.purpleprintproject.auth.command.application.dto.LogoutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Class : AnalysisService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-09       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

@Service
public class AnalysisService {

    private final LogRepository logRepository;
    private final AbnormalBehaviorRepoistory abnormalBehaviorRepoistory;
    private final BehavioralAnalysisService behavioralAnalysisService;

    @Autowired
    public AnalysisService(LogRepository logRepository, AbnormalBehaviorRepoistory abnormalBehaviorRepoistory, BehavioralAnalysisService behavioralAnalysisService) {
        this.logRepository = logRepository;
        this.abnormalBehaviorRepoistory = abnormalBehaviorRepoistory;
        this.behavioralAnalysisService = behavioralAnalysisService;
    }

    @Transactional
    public void saveLogAndAbnormalBehavior(int childId, LogoutDTO logoutDTO) {
        AbnormalBehavior abnormalBehavior = null;
        try {
            abnormalBehavior = abnormalBehaviorRepoistory.save(new AbnormalBehavior(
                    0,
                    logoutDTO.getPointing(),
                    logoutDTO.getJumping(),
                    logoutDTO.getPunching(),
                    childId,
                    new Date(new Date().getTime())
            ));

        } catch(Exception e) {
            throw new SaveLogAndAbnormalBehaviorException("이상행동 저장에 실패하셨습니다.");
        }

        //log 셋팅
        List<Log> saveLogList = new ArrayList<>();
        logoutDTO.getLog().forEach(log -> {
            Log saveLog = new Log(
                    0,
                    log.getXCoord(),
                    log.getZCoord(),
                    childId,
                    new Date(log.getCurrentTime()));

            saveLogList.add(saveLog);
        });

        List<Log> savedLog = null;
        try {
            savedLog = logRepository.saveAll(saveLogList);
        } catch(Exception e) {
            throw new SaveLogAndAbnormalBehaviorException("로그 저장에 실패하셨습니다.");
        }

       behavioralAnalysisService.analysisBehavior(abnormalBehavior, savedLog);
    }
}
