package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.repository.GenreRepository;
import com.alkemy.disney.disney.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenreRepository genreRepository;

    public MovieEntity MovieDTO2Entity (MovieDTO dto){
        MovieEntity movieEntity = new MovieEntity();
        movieEntity.setImage(dto.getImage());
        movieEntity.setTitle(dto.getTitle());
        movieEntity.setCreateDate(dto.getCreateDate());
        movieEntity.setCalification(dto.getCalification());
        return movieEntity;
    }

    public MovieDTO MovieEntity2DTO (MovieEntity entity){
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreateDate(entity.getCreateDate());
        dto.setCalification(entity.getCalification());
        return dto;
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity entity : entities) {
            dtos.add(MovieEntity2DTO(entity));
        }
        return dtos;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreateDate(entity.getCreateDate());
        dto.setCalification(entity.getCalification());
        dto.setGenreId(entity.getGenreId());
        if(loadCharacters){
            List<CharacterDTO> characters = characterMapper.characterSetEntityt2DTO((List<CharacterEntity>) entity.getCharacters(), false);
            dto.setCharacters(characters);
        }
        return dto;
    }


    public MovieEntity movieDTO2EntityUpdate(MovieDTO dto){
        Optional<MovieEntity> result = movieRepository.findById(dto.getId());
        MovieEntity entity = result.get();
        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreateDate(dto.getCreateDate());
        entity.setCalification(dto.getCalification());
        Optional<GenreEntity> resultGenre = genreRepository.findById(dto.getGenreId());
        ///f(!resultGenre.isPresent()){
        entity.setGenreId(dto.getGenreId());
        entity.setCharacters(entity.getCharacters());
        return entity;
    }

}
