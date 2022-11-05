package com.purpleprint.network.purpleprintproject.auth.command.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * <pre>
 * Class : Child
 * Comment: Child entity
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

@Data   //lombok 사용시 class의 모든 필드에 대한 getter/setter/toString/equals 와 같은 함수 사용 가능
@Builder
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
@NoArgsConstructor  // 파라미터가 없는 기본 생성자를 생성
@DynamicInsert      // insert 시 null 인 필드 제외
@DynamicUpdate      // update 시 null인 필드 제외
@Entity
@Table(name = "tbl_child")
public class Child {

    @Id
    @Column(name = "child_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "child_name")
    private String name;

    @Column(name = "connect_num")
    private int connectNum;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "grant_heart")
    @Builder.Default
    private int grantHeart = 5;

    @Column(name = "given_heart")
    private int givenHeart;

}
