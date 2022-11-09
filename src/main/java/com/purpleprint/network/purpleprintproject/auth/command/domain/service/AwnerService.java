package com.purpleprint.network.purpleprintproject.auth.command.domain.service;

import com.purpleprint.network.purpleprintproject.auth.command.application.dto.LogoutDTO;
import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.character.command.domain.model.Character;

/**
 * <pre>
 * Class : OwnerService
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
public interface AwnerService {
    Character selectChildCharacter(Child child);

    void saveLogAndAbnormalBehavior(int childId, LogoutDTO logoutDTO);

}
