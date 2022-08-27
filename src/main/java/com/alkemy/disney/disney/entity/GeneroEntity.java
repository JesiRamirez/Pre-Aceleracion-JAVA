package com.alkemy.disney.disney.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table (name = "Genero")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class GeneroEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String imagen;

}
