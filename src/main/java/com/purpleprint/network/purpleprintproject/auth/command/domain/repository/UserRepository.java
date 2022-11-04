package com.purpleprint.network.purpleprintproject.auth.command.domain.repository;

import com.purpleprint.network.purpleprintproject.auth.command.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>
 * Class : UserRepository
 * Comment: User repository
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-01       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String username);

    int countByUsername(String username);

    int countByEmail(String toEmail);

    User findByNameAndEmail(String name, String email);

    User findByUsernameAndEmail(String username, String email);
}
