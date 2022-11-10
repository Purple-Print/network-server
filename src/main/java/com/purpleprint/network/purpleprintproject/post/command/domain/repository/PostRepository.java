package com.purpleprint.network.purpleprintproject.post.command.domain.repository;

import com.purpleprint.network.purpleprintproject.post.command.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>
 * Class : PostRepository
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-11       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */
@Repository
public interface PostRepository extends JpaRepository <Post, Integer> {
    List<Post> findAllByApprovalAndDeleteYn(String y, String n);
    List<Post> findAllByVideoChildId(int childId);
}
