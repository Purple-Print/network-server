package com.purpleprint.network.purpleprintproject.character.query.service;

import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.character.command.domain.model.Character;
import com.purpleprint.network.purpleprintproject.character.command.domain.respository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 * Class : CharacterService
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
@Service("query.CharacterService")
public class CharacterService {

    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }
    public Character selectChildCharacter(Child child) {

        return characterRepository.findByChildId(child.getId());
    }
}
