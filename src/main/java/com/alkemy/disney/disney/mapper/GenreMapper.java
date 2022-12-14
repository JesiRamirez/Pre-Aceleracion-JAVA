package com.alkemy.disney.disney.mapper;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenreMapper {

    public GenreEntity genreDTO2Entity(GenreDTO dto){
        GenreEntity genreEntity = new GenreEntity();
        genreEntity.setImage(dto.getImage());
        genreEntity.setName(dto.getName());
        return genreEntity;
    }

    public GenreDTO genreEntity2DTO(GenreEntity entity){
        GenreDTO dto = new GenreDTO();
        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setName(entity.getName());
        return dto;

    }

    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities){
        List<GenreDTO> dtos=new ArrayList<>();
        for (GenreEntity entity: entities)
        {
            dtos.add(this.genreEntity2DTO(entity));
        }
        return dtos;
    }

}
