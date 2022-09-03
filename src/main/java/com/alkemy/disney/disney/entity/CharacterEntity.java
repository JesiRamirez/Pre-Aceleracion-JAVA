package com.alkemy.disney.disney.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Character")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CharacterEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    private String name;

    private int age;

    private double weight;

    private String history;

    @ManyToMany(mappedBy = "genre", cascade = CascadeType.ALL)
    private List<MovieEntity> movies = new ArrayList<>();

    //Agregar o eliminar personajes

    public void addMovie(MovieEntity movie){
        this.movies.add(movie);
    }

    public void removeMovie(MovieEntity movie){
        this.movies.add(movie);
    }


}
