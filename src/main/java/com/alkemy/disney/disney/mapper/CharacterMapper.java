package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    public CharacterEntity CharacterDTO2Entity (CharacterDTO dto){
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setImage(dto.getImage());
        characterEntity.setName(dto.getName());
        characterEntity.setAge(dto.getAge());
        characterEntity.setWeight(dto.getWeight());
        characterEntity.setHistory(dto.getHistory());
        return characterEntity;
        }

    public CharacterDTO CharacterEntity2DTO(CharacterEntity entity) {
        CharacterDTO dto = new CharacterDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setHistory((entity.getHistory()));
        return dto;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities){
            dtos.add(CharacterEntity2DTO(entity));
        }
        return dtos;
    }

    public List<CharacterDTO> characterSetEntityt2DTO(List<CharacterEntity> entities, boolean loadMovies) {
        List<CharacterDTO> dtos = new ArrayList<>();
        for (CharacterEntity entity : entities){
            dtos.add(CharacterEntity2DTO(entity));
        }
        return dtos;
    }
}
