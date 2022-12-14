package com.purpleprint.network.purpleprintproject.auth.command.domain.repository;

import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import com.purpleprint.network.purpleprintproject.heart.command.application.dto.RankingDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>
 * Class : ChildRepository
 * Comment: child repository
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-03       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

@Repository
public interface ChildRepository extends JpaRepository<Child, Integer> {

    List<Child> findAllByUserId(Integer id);

    int countByUserId(int userId);

    Child findByName(String username);

    Child findByIdAndUserId(int id, int userId);

    @Query(name = "get_rank_dto", nativeQuery = true)
    List<RankingDTO> findAllRankingInfo();
}
