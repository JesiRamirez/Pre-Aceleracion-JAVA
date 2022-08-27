package com.alkemy.disney.disney.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Personaje")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PersonajeEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String imagen;

    private String nombre;

    private int edad;

    private double peso;

    private String historia;

    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<PeliculaSerieEntity> peliculas = new ArrayList<>();

    //Agregar o eliminar personajes

    public void addPelicula(PeliculaSerieEntity pelicula){
        this.peliculas.add(pelicula);
    }

    public void removePelicula(PeliculaSerieEntity pelicula){
        this.peliculas.add(pelicula);
    }


}
