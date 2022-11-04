package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert
@DynamicUpdate
@Access(AccessType.FIELD)
@Table(name = "tbl_logout")
@SequenceGenerator(
        name = "logout_sequence_generator",
        sequenceName = "seq_logout_id",
        initialValue = 1,
        allocationSize = 50
)
public class Logout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_logout_id")
    @Column(name = "logout_id")
    private int id;

    @Column(name = "logout_x_coord", nullable = false)
    private float xCoord;

    @Column(name = "logout_y_coord", nullable = false)
    private float yCoord;

    @Column(name = "logout_z_coord", nullable = false)
    private float zCoord;

    @Column(name = "logout_at", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date at;

    @Column(name = "login_id", nullable = false)
    private int loginId;

    public Logout() {}

    public Logout(int id, float xCoord, float yCoord, float zCoord, Date at, int loginId) {
        this.id = id;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.zCoord = zCoord;
        this.at = at;
        this.loginId = loginId;
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

    public float getzCoord() {
        return zCoord;
    }

    public void setzCoord(float zCoord) {
        this.zCoord = zCoord;
    }

    public Date getAt() {
        return at;
    }

    public void setAt(Date at) {
        this.at = at;
    }

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    @Override
    public String toString() {
        return "Logout{" +
                "id=" + id +
                ", xCoord=" + xCoord +
                ", yCoord=" + yCoord +
                ", zCoord=" + zCoord +
                ", at=" + at +
                ", loginId=" + loginId +
                '}';
    }
}
