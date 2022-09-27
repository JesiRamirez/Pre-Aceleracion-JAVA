package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.enums.Calification;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor

public class MovieDTO {

    private Long id;

    private String image;

    private String title;

    @Pattern(regexp = "\\d{4}\\d{2}\\d{2}",message = "the date format must be yyyy-MM-dd")
    private LocalDate createDate;

    private Calification calification;

    private Long genreId;

    private List<@Valid CharacterDTO> characters = new ArrayList<>();
}
