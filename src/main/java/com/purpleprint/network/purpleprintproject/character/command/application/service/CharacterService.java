package com.purpleprint.network.purpleprintproject.character.command.application.service;

import com.purpleprint.network.purpleprintproject.character.command.application.dto.CharacterDTO;
import com.purpleprint.network.purpleprintproject.character.command.application.exception.CreateCharacterFailException;
import com.purpleprint.network.purpleprintproject.character.command.domain.model.Character;
import com.purpleprint.network.purpleprintproject.character.command.domain.model.CharacterFile;
import com.purpleprint.network.purpleprintproject.character.command.domain.respository.CharacterFileRepository;
import com.purpleprint.network.purpleprintproject.character.command.domain.respository.CharacterRepository;
import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * Class : CharacterService
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-07       이상학           최초 생성
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterFileRepository characterFileRepository;

    public CharacterService(CharacterRepository characterRepository, CharacterFileRepository characterFileRepository) {
        this.characterRepository = characterRepository;
        this.characterFileRepository = characterFileRepository;
    }

    @Transactional
    public boolean createCharacter(UserDTO userDTO, CharacterDTO characterDTO) {

        try{
            Character newCharacter = characterRepository.save(new Character(
                    0,
                    userDTO.getChild().getChildId(),
                    characterFileRepository.findByName(characterDTO.getFileName())
            ));
        } catch (Exception e) {
            throw new CreateCharacterFailException("캐릭터 생성 실패!");
        }

        return true;
    }
}
