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

    /*public CharacterEntity characterDTO2Entity(CharacterDTO dto) throws ParamNotFound {
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
    }*/


    public CharacterEntity characterDTO2Entity(CharacterDTO dto)
    {
        CharacterEntity characterEntity =new CharacterEntity();

        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setHistory(dto.getHistory());
        return characterEntity;
    }

    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {
        CharacterDTO characterDTO = new CharacterDTO();

        characterDTO.setId(entity.getId());
        characterDTO.setAge(entity.getAge());
        characterDTO.setHistory(entity.getHistory());
        characterDTO.setName(entity.getName());
        characterDTO.setImage(entity.getImage());
        characterDTO.setWeight(entity.getWeight());
        if(loadMovies)
        {
            List<MovieDTO> movieDTOS =this.movieMapper.movieEntityList2DTOList(entity.getMovies(),false);
            characterDTO.setMovies(movieDTOS);
        }
        return characterDTO;
    }

    public void characterEntityRefreshValues(CharacterEntity entity, CharacterDTO characterDTO)
    {
        entity.setAge(characterDTO.getAge());
        entity.setHistory(characterDTO.getHistory());
        entity.setName(characterDTO.getName());
        entity.setWeight(characterDTO.getWeight());
        entity.setImage(characterDTO.getImage());

    }

    public Set<CharacterEntity> characterDTOList2Entity(List<CharacterDTO>dtos)
    {
        Set<CharacterEntity> entities=new HashSet<>();
        for (CharacterDTO dto: dtos)
        {
            entities.add(this.characterDTO2Entity(dto));
        }
        return entities;
    }

    public List<CharacterDTO> characterEntitySet2DTOList(Collection<CharacterEntity> entities, boolean loadMovies)
    {
        List<CharacterDTO> dtos=new ArrayList<>();
        for (CharacterEntity entity: entities)
        {
            dtos.add(this.characterEntity2DTO(entity,loadMovies));
        }
        return dtos;
    }



    public List<CharacterResponseDTO> characterEntitySet2BasicDTOList(Collection<CharacterEntity> entities)
    {
        List<CharacterResponseDTO> dtos=new ArrayList<>();
        CharacterResponseDTO dto;
        for (CharacterEntity entity: entities)
        {
            dto=new CharacterResponseDTO();
            dto.setId(entity.getId());
            dto.setImage(entity.getImage());
            dto.setName(entity.getName());
            dtos.add(dto);


        }
        return dtos;
    }
}
