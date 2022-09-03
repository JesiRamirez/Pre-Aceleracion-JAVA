package com.alkemy.disney.disney.dto;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CharacterDTO {

    private Long id;

    private String image;

    private String name;

    private int age;

    private double weight;

    private String history;

    private List<MovieDTO> movies;
}
