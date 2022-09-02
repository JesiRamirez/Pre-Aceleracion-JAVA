package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GenreDTO;
import org.springframework.stereotype.Service;

@Service

public class GenreServiceImp {

    public GenreDTO save(GenreDTO dto) {
        // TODO: save GENRE
        System.out.println(" GENRE SAVE ");
        return dto;
    }
}
