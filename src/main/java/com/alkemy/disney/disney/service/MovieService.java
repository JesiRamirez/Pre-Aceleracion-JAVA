package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.MovieDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MovieService {
    MovieDTO save(MovieDTO dto);

    List<MovieDTO> getAllMovies();

    MovieDTO update(Long id, MovieDTO dto);

    MovieDTO getById(@PathVariable Long id);
}
