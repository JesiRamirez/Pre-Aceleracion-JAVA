package com.alkemy.disney.disney.dto;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CharacterFilterDTO {

    private String name;

    private String age;

    private Set<Long> movies;

    private String order;

    public CharacterFilterDTO(String name, String age, Set<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.movies = movies;
        this.order = order;
    }

    public boolean isAsc(){
        return order.compareToIgnoreCase("ASC")==0;
    }

    public boolean isDesc(){
        return order.compareToIgnoreCase("DESC")==0;
    }

}
