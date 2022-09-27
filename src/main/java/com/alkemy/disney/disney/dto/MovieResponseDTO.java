package com.alkemy.disney.disney.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class MovieResponseDTO {

    private Long id;

    private String image;

    private String title;

    private LocalDate createDate;

}
