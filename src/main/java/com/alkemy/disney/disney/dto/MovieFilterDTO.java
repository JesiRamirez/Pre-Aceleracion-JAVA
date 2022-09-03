package com.alkemy.disney.disney.dto;

import lombok.*;

import java.util.Set;

@Data


public class MovieFilterDTO {

    private String name;

    private String genre;

    private String order;

    public MovieFilterDTO(String name, String genre, String order) {
        this.name = name;
        this.genre = genre;
        this.order = order;
    }

    public boolean isAsc(){
        return order.compareToIgnoreCase("ASC")==0;
    }

    public boolean isDesc(){
        return order.compareToIgnoreCase("DESC")==0;
    }
}
