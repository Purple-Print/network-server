package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Access(AccessType.FIELD)
@Table(name = "tbl_login")
@SequenceGenerator(
        name = "login_sequence_generator",
        sequenceName = "seq_login_id",
        initialValue = 1,
        allocationSize = 50
)
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "login_sequence_generator")
    @Column(name = "login_id")
    private int id;

    @Column(name = "login_at", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date at;

    @Column(name = "child_id", nullable = false)
    private int childId;

    public Login() {}

    public Login(int id, Date at, int childId) {
        this.id = id;
        this.at = at;
        this.childId = childId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    @Override
    public String toString() {
        return "Login{" +
                "id=" + id +
                ", at=" + at +
                ", childId=" + childId +
                '}';
    }
}
