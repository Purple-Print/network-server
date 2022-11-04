package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Access(AccessType.FIELD)
@Table(name = "tbl_post")
@SequenceGenerator(
        name = "post_sequence_generator",
        sequenceName = "seq_post_id",
        initialValue = 1,
        allocationSize = 50
)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "post_sequence_generator")
    @Column(name = "post_id")
    private int id;

    @Column(name = "post_create_at", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "post_judge_at", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date judgeAt;

    @Column(name = "post_approval", columnDefinition = "char(1)")
    private Character approval;

    @Column(name = "post_delete_yn", columnDefinition = "char(1) default 'N'")
    private char deleteYn;

    @Column(name = "companion_reason", nullable = false, columnDefinition = "varchar(500)")
    private String companionReason;

    @Column(name = "video_id", nullable = false)
    private int videoId;

    public Post() {}

    public Post(int id, Date createAt, Date judgeAt, char approval, char deleteYn, String companionReason, int videoId) {
        this.id = id;
        this.createAt = createAt;
        this.judgeAt = judgeAt;
        this.approval = approval;
        this.deleteYn = deleteYn;
        this.companionReason = companionReason;
        this.videoId = videoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getJudgeAt() {
        return judgeAt;
    }

    public void setJudgeAt(Date judgeAt) {
        this.judgeAt = judgeAt;
    }

    public char getApproval() {
        return approval;
    }

    public void setApproval(char approval) {
        this.approval = approval;
    }

    public char getDeleteYn() {
        return deleteYn;
    }

    public void setDeleteYn(char deleteYn) {
        this.deleteYn = deleteYn;
    }

    public String getCompanionReason() {
        return companionReason;
    }

    public void setCompanionReason(String companionReason) {
        this.companionReason = companionReason;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", createAt=" + createAt +
                ", judgeAt=" + judgeAt +
                ", approval=" + approval +
                ", deleteYn=" + deleteYn +
                ", companionReason='" + companionReason + '\'' +
                ", videoId=" + videoId +
                '}';
    }
}
