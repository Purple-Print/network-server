package com.purpleprint.network.purpleprintproject.post.command.domain.model;

import com.purpleprint.network.purpleprintproject.play.command.domain.model.Video;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * <pre>
 * Class : Post
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

@Data   //lombok 사용시 class의 모든 필드에 대한 getter/setter/toString/equals 와 같은 함수 사용 가능
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
@NoArgsConstructor  // 파라미터가 없는 기본 생성자를 생성
@DynamicInsert      // insert 시 null 인 필드 제외
@DynamicUpdate      // update 시 null인 필드 제외
@Entity
@Table(name = "tbl_post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private int id;

    @Column(name = "post_create_at")
    private Date createAt;

    @Column(name = "post_judge_at")
    private Date judgeAt;

    @Column(name = "post_approval")
    private String approval;

    @Column(name = "post_delete_yn")
    private String deleteYn;

    @Column(name = "companion_reason")
    private String companionReason;

    @OneToOne
    @JoinColumn(name = "video_id")
    private Video video;
}
