package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieResponseDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;

import java.util.List;

public interface MovieService {

    MovieDTO save(MovieDTO dto);

    List<MovieDTO> getAllMovies();

    MovieDTO update(Long id, MovieDTO dto) throws ParamNotFound;

    MovieDTO getById(Long id);

    void delete(Long id);

    List<MovieResponseDTO> getByFilters(String name, String genre, String order);

    void addCharacter(Long movieId, Long characterId) throws ParamNotFound;

    void removeCharacter(Long movieId, Long characterId) throws ParamNotFound;
}
