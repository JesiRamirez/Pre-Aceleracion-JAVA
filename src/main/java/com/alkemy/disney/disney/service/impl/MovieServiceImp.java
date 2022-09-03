package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.mapper.MovieMapper;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImp implements MovieService {

    @Autowired
    private MovieMapper movieMapper;

    @Autowired
    private MovieRepository movieRepository;

    public MovieDTO save(MovieDTO dto){
        MovieEntity entity = movieMapper.MovieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.MovieEntity2DTO(entitySaved);
        return result;
    }

    public List<MovieDTO> getAllMovies() {
        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> result = movieMapper.movieEntityList2DTOList(entities);
        return result;
    }

    @Override
    @Transactional
    public MovieDTO update(Long id, MovieDTO dto) {
        Optional<MovieEntity> result = movieRepository.findById(id);
        MovieEntity entity = result.get();
        dto.setId(id);
        entity = movieMapper.movieDTO2EntityUpdate(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO resultDTO = movieMapper.movieEntity2DTO(entitySaved, true);
        return resultDTO;
    }

    @Override
    public MovieDTO getById(@PathVariable Long id) {
        Optional<MovieEntity> result = movieRepository.findById(id);
        MovieEntity movieEntity = result.get();
        MovieDTO movieDTO = movieMapper.movieEntity2DTO(movieEntity, true);
        return movieDTO;
    }
}






