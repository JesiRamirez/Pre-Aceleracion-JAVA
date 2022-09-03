package com.alkemy.disney.disney.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MovieDTO {

    private Long id;

    private String image;

    private String title;

    private LocalDate createDate;

    private int calification;

    private Long genreId;

    private List<CharacterDTO> characters;
}
