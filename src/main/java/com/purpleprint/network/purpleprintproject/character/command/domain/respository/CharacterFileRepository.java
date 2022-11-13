package com.purpleprint.network.purpleprintproject.character.command.domain.respository;

import com.purpleprint.network.purpleprintproject.character.command.domain.model.CharacterFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * <pre>
 * Class : CharacterFileRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-08       이상학           최초 생성
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
public interface CharacterFileRepository extends JpaRepository<CharacterFile, Integer> {

//    @Query(value = "SELECT \n" +
//            "C.id, C.url, C.name " +
//            "FROM CharacterFile C " +
//            "WHERE C.name = :fileName")
    CharacterFile findByName(String fileName);

}
