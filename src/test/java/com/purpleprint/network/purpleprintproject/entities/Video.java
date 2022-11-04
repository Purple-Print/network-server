package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert      // insert 시 null 인 필드 제외
@DynamicUpdate      // update 시 null인 필드 제외
@Access(AccessType.FIELD)
@Table(name = "tbl_video")
@SequenceGenerator(
        name = "video_sequence_generator",
        sequenceName = "seq_video_id",
        initialValue = 1,
        allocationSize = 50
)
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "video_sequence_generator")
    @Column(name = "video_id")
    private int id;

    @Column(name = "video_url", nullable = false, columnDefinition = "varchar(500)")
    private String url;

    @Column(name = "video_original_name", nullable = false, columnDefinition = "varchar(100)")
    private String originalName;

    @Column(name = "video_conversion_name", nullable = false, columnDefinition = "varchar(100)")
    private String conversionName;

    @Column(name = "create_at", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "child_id", nullable = false)
    private int childId;

    @Column(name = "video_delete_yn", columnDefinition = "char(1) default 'N'")
    private char deleteYn;

    public Video() {}

    public Video(int id, String url, String originalName, String conversionName, Date createAt, int childId, char deleteYn) {
        this.id = id;
        this.url = url;
        this.originalName = originalName;
        this.conversionName = conversionName;
        this.createAt = createAt;
        this.childId = childId;
        this.deleteYn = deleteYn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

    public String getConversionName() {
        return conversionName;
    }

    public void setConversionName(String conversionName) {
        this.conversionName = conversionName;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public char getDeleteYn() {
        return deleteYn;
    }

    public void setDeleteYn(char deleteYn) {
        this.deleteYn = deleteYn;
    }

    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", originalName='" + originalName + '\'' +
                ", conversionName='" + conversionName + '\'' +
                ", createAt=" + createAt +
                ", childId=" + childId +
                ", deleteYn=" + deleteYn +
                '}';
    }
}
