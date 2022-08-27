package com.alkemy.disney.disney.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "Genre")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class GenreEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String image;

}
