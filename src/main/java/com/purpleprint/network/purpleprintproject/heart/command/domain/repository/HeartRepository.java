package com.purpleprint.network.purpleprintproject.heart.command.domain.repository;

import com.purpleprint.network.purpleprintproject.heart.command.application.dto.HeartDTO;
import com.purpleprint.network.purpleprintproject.heart.command.domain.model.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Class : HeartRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       이상학           최초 생성
 * </pre>
 *
 * @author 이상학(최초 작성자)
 * @version 1(클래스 버전)
 */
@Repository
public interface HeartRepository extends JpaRepository<Heart, Integer> {
    List<Heart> findByGiverAndGaveAt(int giver, Date date);
}
