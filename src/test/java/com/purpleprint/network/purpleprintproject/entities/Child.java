package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Access(AccessType.FIELD)
@Table(name = "tbl_child")
@SequenceGenerator(
        name = "child_sequence_generator",
        sequenceName = "seq_user_id",
        initialValue = 1,
        allocationSize = 50
)
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "child_sequence_generator")
    @Column(name = "child_id")
    private int id;

    @Column(name = "child_name", nullable = false, columnDefinition = "varchar(50)")
    private String name;

    @Column(name = "connect_num", columnDefinition = "int default '0'")
    private int connectNum;

    @Column(name = "user_id", nullable = false, columnDefinition = "int")
    private int userId;

    @Column(name = "grant_heart", columnDefinition = "int default '5'")
    private int grantHeart;

    @Column(name = "given_heart", columnDefinition = "int default '0'")
    private int givenHeart;

    public Child() {}

    public Child(int id, String name, int connectNum, int userId, int grantHeart, int givenHeart) {
        this.id = id;
        this.name = name;
        this.connectNum = connectNum;
        this.userId = userId;
        this.grantHeart = grantHeart;
        this.givenHeart = givenHeart;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConnectNum() {
        return connectNum;
    }

    public void setConnectNum(int connectNum) {
        this.connectNum = connectNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGrantHeart() {
        return grantHeart;
    }

    public void setGrantHeart(int grantHeart) {
        this.grantHeart = grantHeart;
    }

    public int getGivenHeart() {
        return givenHeart;
    }

    public void setGivenHeart(int givenHeart) {
        this.givenHeart = givenHeart;
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", connectNum=" + connectNum +
                ", userId=" + userId +
                ", grantHeart=" + grantHeart +
                ", givenHeart=" + givenHeart +
                '}';
    }
}
