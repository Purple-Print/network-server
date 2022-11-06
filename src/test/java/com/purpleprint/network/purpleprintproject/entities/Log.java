package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Access(AccessType.FIELD)
@Table(name = "tbl_log")
@SequenceGenerator(
        name = "log_sequence_generator",
        sequenceName = "seq_log_id",
        initialValue = 1,
        allocationSize = 50
)
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "log_sequence_generator")
    @Column(name = "log_id")
    private int id;

    @Column(name = "log_x_coord", nullable = false)
    private float xCoord;

    @Column(name = "log_y_coord", nullable = false)
    private float yCoord;

    @Column(name = "child_id", nullable = false)
    private int childId;

    @Column(name = "log_at", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date at;

    public Log() {}

    public Log(int id, float xCoord, float yCoord, int childId, Date at) {
        this.id = id;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.childId = childId;
        this.at = at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getxCoord() {
        return xCoord;
    }

    public void setxCoord(float xCoord) {
        this.xCoord = xCoord;
    }

    public float getyCoord() {
        return yCoord;
    }

    public void setyCoord(float yCoord) {
        this.yCoord = yCoord;
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
        return "Log{" +
                "id=" + id +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                ", childId=" + childId +
                ", at=" + at +
                '}';
    }
}
