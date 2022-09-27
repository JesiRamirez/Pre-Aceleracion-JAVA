package com.alkemy.disney.disney.dto;

import lombok.*;
import java.util.Set;

@Data

public class CharacterFilterDTO {

    private String name;

    private int age;

    private Set<Long> movies;

    private String order;

    public CharacterFilterDTO(String name, int age, Set<Long> movies, String order) {
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
