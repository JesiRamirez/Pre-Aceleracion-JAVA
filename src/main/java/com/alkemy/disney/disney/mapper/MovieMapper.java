package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieResponseDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
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

    public MovieEntity movieDTO2Entity(MovieDTO dto) throws ParamNotFound {
        MovieEntity movieEntity;
        if (dto.getId() == null) {
            movieEntity = new MovieEntity();
            movieEntity.setImage(dto.getImage());
            movieEntity.setTitle(dto.getTitle());
            movieEntity.setCreateDate(dto.getCreateDate());
            movieEntity.setCalification(dto.getCalification());
            Optional<GenreEntity> resultGenre = genreRepository.findById(dto.getId());
            if (!resultGenre.isPresent()) {
                throw new ParamNotFound(("genre id not found"));
            }
            movieEntity.setGenreId(dto.getGenreId());
            movieEntity.setCharacters(characterMapper.characterDTOList2EntitySetMovieCreation(dto.getCharacters()));
            return movieEntity;
        } else {
            Optional<MovieEntity> result = movieRepository.findById(dto.getId());
            if (result.isPresent()) {
                movieEntity = result.get();
                movieEntity.setCharacters(characterMapper.characterDTOList2EntitySetMovieCreation(dto.getCharacters()));
                return movieEntity;
            } else {
                throw new ParamNotFound("movie id not found");
            }
        }
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {
        MovieDTO dto = new MovieDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreateDate(entity.getCreateDate());
        dto.setCalification(entity.getCalification());
        dto.setGenreId(entity.getGenreId());
        if (loadCharacters) {
            List<CharacterDTO> characters = characterMapper.characterSetEntity2DTO(entity.getCharacters(), false);
            dto.setCharacters(characters);
        }
        return dto;
    }

    public MovieEntity movieDTO2EntityUpdate(MovieDTO dto) throws ParamNotFound {
        Optional<MovieEntity> result = movieRepository.findById(dto.getId());
        if (result.isPresent()) {
            MovieEntity entity = result.get();
            entity.setImage(dto.getImage());
            entity.setTitle(dto.getTitle());
            entity.setCreateDate(dto.getCreateDate());
            entity.setCalification(dto.getCalification());
            Optional<GenreEntity> resultGenre = genreRepository.findById(dto.getGenreId());
            if (!resultGenre.isPresent()) {
                throw new ParamNotFound("genre id not found");
            }
            entity.setGenreId(dto.getGenreId());
            entity.setCharacters(entity.getCharacters());
            return entity;
        } else {
            throw new ParamNotFound("movie id not found");
        }
    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters) {
        List<MovieDTO> dtos = new ArrayList<>();
        for (MovieEntity aux : entities) {
            dtos.add(movieEntity2DTO(aux, loadCharacters));
        }
        return dtos;
    }

    public MovieResponseDTO movieEntity2BasicDTO(MovieEntity entity){
        MovieResponseDTO basicDTO = new MovieResponseDTO();
        basicDTO.setImage(entity.getImage());
        basicDTO.setTitle(entity.getTitle());
        basicDTO.setCreateDate(entity.getCreateDate());
        return basicDTO;
    }

    public List<MovieResponseDTO> movieEntityList2BasicDTO(List<MovieEntity> entities){
        List<MovieResponseDTO> basicDTOs = new ArrayList<>();
        for(MovieEntity aux : entities){
            basicDTOs.add(movieEntity2BasicDTO(aux));
        }
        return basicDTOs;
    }
}