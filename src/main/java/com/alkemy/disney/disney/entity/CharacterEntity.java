package com.alkemy.disney.disney.entity;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Characters")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE Characters SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")

public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String name;

    private int age;

    private double weight;

    private String history;

    @ManyToMany(mappedBy = "characters", cascade = CascadeType.PERSIST)
    public List<MovieEntity> movies = new ArrayList<>();

    private boolean deleted = Boolean.FALSE;
}

