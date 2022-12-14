package com.purpleprint.network.purpleprintproject.analysis.command.domain.service;

import com.purpleprint.network.purpleprintproject.analysis.command.domain.model.AbnormalBehavior;
import com.purpleprint.network.purpleprintproject.analysis.command.domain.model.Log;

import java.util.List;

/**
 * <pre>
 * Class : BehaviorAnalysisService
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
public interface BehavioralAnalysisService {

    void analysisBehavior(AbnormalBehavior abnormalBehavior, List<Log> savedLog);
}
