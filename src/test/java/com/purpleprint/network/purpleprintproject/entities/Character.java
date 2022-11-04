package com.purpleprint.network.purpleprintproject.entities;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tbl_character")
@Access(AccessType.FIELD)
@SequenceGenerator(
        name = "character_sequence_generator",
        sequenceName = "seq_character_id",
        initialValue = 1,
        allocationSize = 50
)
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "character_sequence_generator")
    @Column(name = "character_id")
    private int id;

    @Column(name = "child_id", nullable = false)
    private int childId;

    @Column(name = "characterfile_id", nullable = false)
    private int characterfileId;

    public Character() {}

    public Character(int id, int childId, int characterfileId) {
        this.id = id;
        this.childId = childId;
        this.characterfileId = characterfileId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public int getCharacterfileId() {
        return characterfileId;
    }

    public void setCharacterfileId(int characterfileId) {
        this.characterfileId = characterfileId;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", childId=" + childId +
                ", characterfileId=" + characterfileId +
                '}';
    }
}
