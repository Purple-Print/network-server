package com.purpleprint.network.purpleprintproject.character.command.application.service;

import com.purpleprint.network.purpleprintproject.character.command.application.dto.CharacterDTO;
import com.purpleprint.network.purpleprintproject.character.command.application.exception.CreateCharacterFailException;
import com.purpleprint.network.purpleprintproject.character.command.application.exception.PictureReceiveFailException;
import com.purpleprint.network.purpleprintproject.character.command.domain.model.Character;
import com.purpleprint.network.purpleprintproject.character.command.domain.model.CharacterFile;
import com.purpleprint.network.purpleprintproject.character.command.domain.respository.CharacterFileRepository;
import com.purpleprint.network.purpleprintproject.character.command.domain.respository.CharacterRepository;
import com.purpleprint.network.purpleprintproject.character.command.domain.service.RecommendService;
import com.purpleprint.network.purpleprintproject.common.dto.UserDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
    private final RecommendService recommendService;


    public CharacterService(CharacterRepository characterRepository, CharacterFileRepository characterFileRepository, RecommendService recommendService) {
        this.characterRepository = characterRepository;
        this.characterFileRepository = characterFileRepository;
        this.recommendService = recommendService;
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
//
//    public String recommendCharacter(UserDTO userDTO, MultipartFile imageFile) throws IOException {
////        imageFile.transferTo(new File("D:\\" + imageFile.getOriginalFilename()));
////        return imageFile;
//        String imagePath = null;
//        String absolutePath = new File("").getAbsolutePath() + "\\";
////        String path = "images/profile";
////        File file = new File(path);
////        if(!file.exists()) {
////            file.mkdirs();
////        }
//
//        if(!imageFile.isEmpty()) {
//            String contentType = imageFile.getContentType();
//            String originalFileExtension;
//            if (ObjectUtils.isEmpty(contentType)) {
//                throw new PictureReceiveFailException("이미지 파일은 jpg, png 만 가능합니다.");
//            } else {
//                if (contentType.contains("image/jpeg")) {
//                    originalFileExtension = ".jpg";
//                } else if (contentType.contains("image/png")) {
//                    originalFileExtension = ".png";
//                } else {
//                    throw new PictureReceiveFailException("이미지 파일은 jpg, png 만 가능합니다.");
//                }
//            }
////            imagePath = path + "/" + userDTO.getChild().getChildName() + originalFileExtension;
////            file = new File(absolutePath + imagePath);
////            imageFile.transferTo(file);
//
//            String recommendInfo = recommendService.getRecommend(imageFile);
//        }
//        else {
//            throw new PictureReceiveFailException("이미지 파일이 비어있습니다.");
//        }
//
//        return imagePath;
//
//    }
}
