package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterResponseDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CharacterMapper {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieMapper movieMapper;

    public CharacterEntity characterDTO2Entity(CharacterDTO dto) throws ParamNotFound {
        CharacterEntity characterEntity;
        if (dto.getId() == null) {
            characterEntity = new CharacterEntity();
            characterEntity.setImage(dto.getImage());
            characterEntity.setName(dto.getName());
            characterEntity.setAge(dto.getAge());
            characterEntity.setWeight(dto.getWeight());
            characterEntity.setHistory(dto.getHistory());
            return characterEntity;
        }else{
            Optional<CharacterEntity> result = characterRepository.findById(dto.getId());
            if(result.isPresent()){
                characterEntity = result.get();
                characterEntity.setImage(dto.getImage());
                characterEntity.setName(dto.getName());
                characterEntity.setAge(dto.getAge());
                characterEntity.setWeight(dto.getWeight());
                characterEntity.setHistory(dto.getHistory());
                return characterEntity;
            }else{
                throw new ParamNotFound("character id not found");
            }
        }
    }

    public CharacterEntity characterDTO2EntityMovieCreation(CharacterDTO dto) throws ParamNotFound{
        CharacterEntity characterEntity;
        if(dto.getId()==null){
            characterEntity = new CharacterEntity();
            characterEntity.setImage(dto.getImage());
            characterEntity.setName(dto.getName());
            characterEntity.setAge(dto.getAge());
            characterEntity.setWeight(dto.getWeight());
            characterEntity.setHistory(dto.getHistory());
            return characterEntity;
        }else{
            Optional<CharacterEntity> result = characterRepository.findById(dto.getId());
            if(result.isPresent()){
                characterEntity = result.get();
                return characterEntity;
            }else{
                throw new ParamNotFound("character id not found");
            }
        }
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory((entity.getHistory()));
        if(loadMovies){
            List<MovieDTO> movies = movieMapper.movieEntityList2DTOList(entity.getMovies(), false);
            dto.setMovies(movies);
        }
        return dto;
    }

    public List<CharacterDTO> characterSetEntity2DTO(Set<CharacterEntity> entities, boolean loadMovies){
        List<CharacterDTO> dtos = new ArrayList<>();
        for(CharacterEntity aux : entities){
            dtos.add(characterEntity2DTO(aux, loadMovies));
        }
        return dtos;
    }

    public List<CharacterDTO> characterListEntity2DTO(List<CharacterEntity> entities, boolean loadMovies){
        List<CharacterDTO> dtos = new ArrayList<>();
        for(CharacterEntity aux : entities){
            dtos.add(characterEntity2DTO(aux, loadMovies));
        }
        return dtos;
    }

    public Set<CharacterEntity> characterDTOList2EntitySetMovieCreation(List<CharacterDTO> dtos) throws ParamNotFound{
        Set<CharacterEntity> entities = new HashSet<>();
        for(CharacterDTO aux : dtos){
            entities.add(characterDTO2EntityMovieCreation(aux));
        }
        return entities;
    }

    public CharacterResponseDTO characterEntity2BasicDTO(CharacterEntity entity){
        CharacterResponseDTO basicDTO = new CharacterResponseDTO();
        basicDTO.setImage(entity.getImage());
        basicDTO.setName(entity.getName());
        return basicDTO;
    }

    public List<CharacterResponseDTO> characterEntityList2BasicDTO(List<CharacterEntity> entities){
        List<CharacterResponseDTO> basicDTOs = new ArrayList<>();
        for(CharacterEntity aux : entities){
            basicDTOs.add(characterEntity2BasicDTO(aux));
        }
        return basicDTOs;
    }
}
