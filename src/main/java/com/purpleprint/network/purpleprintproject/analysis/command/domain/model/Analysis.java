package com.purpleprint.network.purpleprintproject.analysis.command.domain.model;

import com.purpleprint.network.purpleprintproject.auth.command.domain.model.Child;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <pre>
 * Class : Analysis
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
@Table(name = "tbl_analysis")
public class Analysis {

    @Id
    @Column(name = "analysis_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "behavior_feedback")
    private String behaviorFeedback;

    @Column(name = "final_feedback")
    private String finalFeedback;

    @ManyToOne
    @JoinColumn(name = "friend1")
    private Child friend1;

    @ManyToOne
    @JoinColumn(name = "friend2")
    private Child friend2;

    @ManyToOne
    @JoinColumn(name = "friend3")
    private Child friend3;

    @ManyToOne
    @JoinColumn(name = "friend4")
    private Child friend4;

    @ManyToOne
    @JoinColumn(name = "friend5")
    private Child friend5;

    @ManyToOne
    @JoinColumn(name = "child_id")
    private Child childId;

    @Column(name = "send_yn")
    private String sendYn;

}
