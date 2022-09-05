package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.CharacterBasicDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.dto.CharacterFilterDTO;
import com.alkemy.disney.disney.entity.CharacterEntity;
import com.alkemy.disney.disney.entity.MovieEntity;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.mapper.CharacterMapper;
import com.alkemy.disney.disney.repository.CharacterRepository;
import com.alkemy.disney.disney.repository.MovieRepository;
import com.alkemy.disney.disney.repository.specification.CharacterSpecification;
import com.alkemy.disney.disney.service.CharacterService;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class CharacterServiceImp implements CharacterService {

    @Autowired
    private CharacterMapper characterMapper;

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CharacterSpecification characterSpecification;



    @Transactional
    public CharacterDTO save(CharacterDTO dto) throws ParamNotFound {
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entity, true);
        return result;
    }

    @Transactional
    public CharacterDTO update(Long id, CharacterDTO dto) throws ParamNotFound{
        Optional<CharacterEntity> result = characterRepository.findById(id);
        if (result.isPresent()){
            CharacterEntity entity = result.get();
            dto.setId(id);
            entity = characterMapper.characterDTO2Entity(dto);
            CharacterEntity entitySaved = characterRepository.save(entity);
            CharacterDTO resultDTO = characterMapper.characterEntity2DTO(entitySaved, true);
            return resultDTO;
        }else{
            throw new ParamNotFound("character id not found");
        }
    }

    @Override
    public List<CharacterDTO> getAllCharacter() {
        List<CharacterEntity> entities = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterListEntity2DTO(entities, true);
        return result;
    }

    @Override
    public CharacterDTO getById(Long id) {
        Optional <CharacterEntity> result = characterRepository.findById(id);
        if(result.isPresent()) {
            CharacterEntity characterEntity = result.get();
            CharacterDTO characterDTO = characterMapper.characterEntity2DTO(characterEntity, true);
            return characterDTO;
        }else{
            throw new ParamNotFound("character id not found");
        }
    }

    @Override
    public void delete(Long id) {
        Optional<CharacterEntity> result = characterRepository.findById(id);
        if(result.isPresent()){
            for(MovieEntity auxMovie : movieRepository.findAll()){
                if (auxMovie.getCharacters().contains(result.get())){
                    movieService.removeCharacter(auxMovie.getId(), result.get().getId());
                }
            }
            characterRepository.deleteById(id);
        }else{
            throw new ParamNotFound(" character id not found");
        }
    }

    @Override
    public List<CharacterBasicDTO> getByFilters(String name, String age, Set<Long> movies, String order) {
        CharacterFilterDTO filtersDTO =new CharacterFilterDTO(name, age, movies, order);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterBasicDTO> basicDTOs = characterMapper.characterEntityList2BasicDTO(entities);
        return basicDTOs;
    }


}
