package com.purpleprint.network.purpleprintproject.auth.command.domain.repository;

import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Class : LoginRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-04       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {
    Login findByChildIdOrderByIdDesc(int childId);
}
