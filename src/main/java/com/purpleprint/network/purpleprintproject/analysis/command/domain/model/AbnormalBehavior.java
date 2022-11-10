package com.purpleprint.network.purpleprintproject.analysis.command.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * <pre>
 * Class : AbnormalBehavior
 * Comment: 클래스에 대한 간단 설명
 * History
 * ================================================================
 * DATE             AUTHOR           NOTE
 * ----------------------------------------------------------------
 * 2022-11-09       전현정           최초 생성
 * </pre>
 *
 * @author 전현정(최초 작성자)
 * @version 1(클래스 버전)
 * @see
 */

@Data   //lombok 사용시 class의 모든 필드에 대한 getter/setter/toString/equals 와 같은 함수 사용 가능
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자를 만듦
@NoArgsConstructor  // 파라미터가 없는 기본 생성자를 생성
@Entity
@Table(name = "tbl_abnormal_behavior")
public class AbnormalBehavior {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "abnormal_behavior_id")
    private int id;

    @Column(name = "pointing")
    private Float pointing;

    @Column(name = "jumping")
    private Float jumping;

    @Column(name = "punching")
    private Float punching;

    @Column(name = "child_id")
    private int childId;

    @Column(name = "abnormal_behavior_at")
    private Date abnormalBebaviorAt;

}
