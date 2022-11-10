package com.purpleprint.network.purpleprintproject.auth.command.infra.service;

import com.purpleprint.network.purpleprintproject.analysis.command.application.service.AnalysisService;
import com.purpleprint.network.purpleprintproject.auth.command.application.dto.LogoutDTO;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.auth.command.domain.service.AwnerService;
import com.purpleprint.network.purpleprintproject.character.command.domain.model.Character;
import com.purpleprint.network.purpleprintproject.character.query.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : OwnerServiceImpl
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-06       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Service
public class AwnerServiceImpl implements AwnerService {

    private final CharacterService characterService;
    private final AnalysisService analysisService;

    @Autowired
    public AwnerServiceImpl(CharacterService characterService, AnalysisService analysisService) {
        this.characterService = characterService;
        this.analysisService = analysisService;
    }

    @Override
    public Character selectChildCharacter(Child child) {

        return characterService.selectChildCharacter(child);
    }

    @Override
    public void saveLogAndAbnormalBehavior(int childId, LogoutDTO logoutDTO) {
        analysisService.saveLogAndAbnormalBehavior(childId, logoutDTO);
    }
}
