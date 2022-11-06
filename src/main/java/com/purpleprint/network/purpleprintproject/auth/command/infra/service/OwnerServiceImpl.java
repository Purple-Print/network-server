package com.purpleprint.network.purpleprintproject.auth.command.infra.service;

import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.auth.command.domain.service.OwnerService;
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
public class OwnerServiceImpl implements OwnerService {

    private final CharacterService characterService;

    @Autowired
    public OwnerServiceImpl(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Override
    public Character selectChildCharacter(Child child) {

        return characterService.selectChildCharacter(child);
    }
}
