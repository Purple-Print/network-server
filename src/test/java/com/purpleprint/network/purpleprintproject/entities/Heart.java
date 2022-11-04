package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tbl_heart")
@Access(AccessType.FIELD)
@SequenceGenerator(
        name = "heart_sequence_generator",
        sequenceName = "seq_heart_id",
        initialValue = 1,
        allocationSize = 50
)
public class Heart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "heart_sequence_generator")
    @Column(name = "heart_id")
    private int id;

    @Column(name = "giver", nullable = false)
    private int giver;

    @Column(name = "recipient", nullable = false)
    private int recipient;

    public Heart() {}

    public Heart(int id, int giver, int recipient) {
        this.id = id;
        this.giver = giver;
        this.recipient = recipient;
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

    @Override
    public String toString() {
        return "Heart{" +
                "id=" + id +
                ", giver=" + giver +
                ", recipient=" + recipient +
                '}';
    }
}
