package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;

import java.util.List;

public interface GenreService {

    GenreDTO save(GenreDTO dto) throws ParamNotFound;

}
