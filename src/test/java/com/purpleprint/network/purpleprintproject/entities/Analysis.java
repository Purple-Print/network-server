package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tbl_analysis")
@SequenceGenerator(
        name = "analysis_sequence_generator",
        sequenceName = "seq_analysis_id",
        initialValue = 1,
        allocationSize = 50
)
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "analysis_sequence_generator")
    @Column(name = "analysis_id")
    private int id;

    @Column(name = "behavior_feedback", nullable = false, columnDefinition = "varchar(500)")
    private String behaviorFeedback;

    @Column(name = "final_feedback", nullable = false, columnDefinition = "varchar(500)")
    private String finalFeedback;

    @Column(name = "child_id", nullable = false)
    private int childId;

    @Column(name = "friend1")
    private Integer friend1;

    @Column(name = "friend2")
    private Integer friend2;

    @Column(name = "friend3")
    private Integer friend3;

    @Column(name = "friend4")
    private Integer friend4;

    @Column(name = "friend5")
    private Integer friend5;

    public Analysis() {}

    public Analysis(int id, String behaviorFeedback, String finalFeedback, int childId, int friend1, int friend2, int friend3, int friend4, int friend5) {
        this.id = id;
        this.behaviorFeedback = behaviorFeedback;
        this.finalFeedback = finalFeedback;
        this.childId = childId;
        this.friend1 = friend1;
        this.friend2 = friend2;
        this.friend3 = friend3;
        this.friend4 = friend4;
        this.friend5 = friend5;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBehaviorFeedback() {
        return behaviorFeedback;
    }

    public void setBehaviorFeedback(String behaviorFeedback) {
        this.behaviorFeedback = behaviorFeedback;
    }

    public String getFinalFeedback() {
        return finalFeedback;
    }

    public void setFinalFeedback(String finalFeedback) {
        this.finalFeedback = finalFeedback;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public int getFriend1() {
        return friend1;
    }

    public void setFriend1(int friend1) {
        this.friend1 = friend1;
    }

    public int getFriend2() {
        return friend2;
    }

    public void setFriend2(int friend2) {
        this.friend2 = friend2;
    }

    public int getFriend3() {
        return friend3;
    }

    public void setFriend3(int friend3) {
        this.friend3 = friend3;
    }

    public int getFriend4() {
        return friend4;
    }

    public void setFriend4(int friend4) {
        this.friend4 = friend4;
    }

    public int getFriend5() {
        return friend5;
    }

    public void setFriend5(int friend5) {
        this.friend5 = friend5;
    }

    @Override
    public String toString() {
        return "Analysis{" +
                "id=" + id +
                ", behaviorFeedback='" + behaviorFeedback + '\'' +
                ", finalFeedback='" + finalFeedback + '\'' +
                ", childId=" + childId +
                ", friend1=" + friend1 +
                ", friend2=" + friend2 +
                ", friend3=" + friend3 +
                ", friend4=" + friend4 +
                ", friend5=" + friend5 +
                '}';
    }
}
