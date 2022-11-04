package com.purpleprint.network.purpleprintproject.entities;

import com.purpleprint.network.purpleprintproject.RoleType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Entity
@DynamicInsert      // insert 시 null 인 필드 제외
@DynamicUpdate      // update 시 null인 필드 제외
@Access(AccessType.FIELD)
@Table(name = "tbl_user")
@SequenceGenerator(
        name = "user_sequence_generator",
        sequenceName = "seq_user_id",
        initialValue = 1,
        allocationSize = 50
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_sequence_generator")
    @Column(name = "user_id")
    private int id;

    @Column(name = "user_username", nullable = false, unique = true, columnDefinition = "varchar(100)")
    private String username;

    @Column(name = "user_pwd", nullable = false, columnDefinition = "varchar(300)")
    private String pwd;

    @Column(name = "user_email", nullable = false, columnDefinition = "varchar(50)")
    private String email;

    @Column(name = "user_name", nullable = false, columnDefinition = "varchar(30)")
    private String name;

    @Column(name = "user_role", nullable = false, columnDefinition = "varchar(10)")
    private RoleType role;

    @Column(name = "temp_pwd", columnDefinition = "char(1) default 'N'")
    private char tempPwd;

    @Column(name = "user_sec_yn", columnDefinition = "char(1) default 'N'")
    private char userSecYn;

    @Column(name = "user_sec_at", columnDefinition = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userSecAt;

    public User() {}

    public User(int id, String username, String pwd, String email, String name, RoleType role, char tempPwd, char userSecYn, Date userSecAt) {
        this.id = id;
        this.username = username;
        this.pwd = pwd;
        this.email = email;
        this.name = name;
        this.role = role;
        this.tempPwd = tempPwd;
        this.userSecYn = userSecYn;
        this.userSecAt = userSecAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public char getTempPwd() {
        return tempPwd;
    }

    public void setTempPwd(char tempPwd) {
        this.tempPwd = tempPwd;
    }

    public char getUserSecYn() {
        return userSecYn;
    }

    public void setUserSecYn(char userSecYn) {
        this.userSecYn = userSecYn;
    }

    public Date getUserSecAt() {
        return userSecAt;
    }

    public void setUserSecAt(Date userSecAt) {
        this.userSecAt = userSecAt;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", tempPwd='" + tempPwd + '\'' +
                ", userSecYn='" + userSecYn + '\'' +
                ", userSecAt=" + userSecAt +
                '}';
    }
}
