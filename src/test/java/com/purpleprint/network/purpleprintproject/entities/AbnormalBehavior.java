package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Access(AccessType.FIELD)
@Table(name = "tbl_abnormal_behavior")
@SequenceGenerator(
        name = "abnormal_behavior_sequence_generator",
        sequenceName = "seq_abnormal_behavior_id",
        initialValue = 1,
        allocationSize = 50
)
public class AbnormalBehavior {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "abnormal_behavior_sequence_generator")
    @Column(name = "abnormal_behavior_id")
    private int id;

    @Column(name = "pointing", nullable = false)
    private float pointing;

    @Column(name = "jumping", nullable = false)
    private float jumping;

    @Column(name = "punching", nullable = false)
    private float punching;

    @Column(name = "child_id", nullable = false)
    private int childId;

    @Column(name = "abnormal_behavior_at", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date at;

    public AbnormalBehavior() {}

    public AbnormalBehavior(int id, float pointing, float jumping, float punching, int childId, Date at) {
        this.id = id;
        this.pointing = pointing;
        this.jumping = jumping;
        this.punching = punching;
        this.childId = childId;
        this.at = at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPointing() {
        return pointing;
    }

    public void setPointing(float pointing) {
        this.pointing = pointing;
    }

    public float getJumping() {
        return jumping;
    }

    public void setJumping(float jumping) {
        this.jumping = jumping;
    }

    public float getPunching() {
        return punching;
    }

    public void setPunching(float punching) {
        this.punching = punching;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "AbnormalBehavior{" +
                "id=" + id +
                ", pointing=" + pointing +
                ", jumping=" + jumping +
                ", punching=" + punching +
                ", childId=" + childId +
                ", at=" + at +
                '}';
    }
}
