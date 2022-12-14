package com.purpleprint.network.purpleprintproject.heart.command.domain.model;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tbl_heart")
@Access(AccessType.FIELD)
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart_id")
    private Integer id;

    @Column(name = "giver", nullable = false)
    private int giver;

    @Column(name = "recipient", nullable = false)
    private int recipient;

    @Column(name = "gave_at", columnDefinition = "timestamp default now()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date gaveAt;

    public Heart() {}

    public Heart(int id, int giver, int recipient, Date gaveAt) {
        this.id = id;
        this.giver = giver;
        this.recipient = recipient;
        this.gaveAt = gaveAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGiver() {
        return giver;
    }

    public void setGiver(int giver) {
        this.giver = giver;
    }

    public int getRecipient() {
        return recipient;
    }

    public void setRecipient(int recipient) {
        this.recipient = recipient;
    }

    public Date getGaveAt() {
        return gaveAt;
    }

    public void setGaveAt(Date gaveAt) {
        this.gaveAt = gaveAt;
    }

    @Override
    public String toString() {
        return "Heart{" +
                "id=" + id +
                ", giver=" + giver +
                ", recipient=" + recipient +
                ", gaveAt=" + gaveAt +
                '}';
    }
}
