package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CharacterServiceImp implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterRepository characterRepository;


    @Override
    public CharacterDTO save(CharacterDTO dto) {
        CharacterEntity entity = characterMapper.CharacterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.CharacterEntity2DTO(entity);
        return result;
    }

    @Override
    public List<CharacterDTO> getAllCharacter() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(entities);
        return result;
    }


}
