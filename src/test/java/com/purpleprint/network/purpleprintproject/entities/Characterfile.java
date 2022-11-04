package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
@Entity
@DynamicInsert
@DynamicUpdate
@Access(AccessType.FIELD)
@Table(name = "tbl_characterfile")
@SequenceGenerator(
        name = "characterfile_sequence_generator",
        sequenceName = "seq_characterfile_id",
        initialValue = 1,
        allocationSize = 50
)
public class Characterfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "characterfile_sequence_generator")
    @Column(name = "characterfile_id")
    private int id;

    @Column(name = "characterfile_url", nullable = false, columnDefinition = "varchar(500)")
    private String url;

    @Column(name = "characterfile_name", nullable = false, columnDefinition = "varchar(100)")
    private String name;

    public Characterfile() {}

    public Characterfile(int id, String url, String name) {
        this.id = id;
        this.url = url;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Characterfile{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
