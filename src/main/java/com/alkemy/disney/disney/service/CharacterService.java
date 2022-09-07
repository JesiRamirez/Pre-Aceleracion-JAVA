package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.CharacterResponseDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    CharacterDTO save(CharacterDTO dto) throws ParamNotFound;

    CharacterDTO update(Long id, CharacterDTO dto) throws ParamNotFound;

    List<CharacterDTO> getAllCharacter();

    CharacterDTO getById(Long id);

    void delete(Long id);

    List<CharacterResponseDTO> getByFilters(String name, String age, Set<Long> movies, String order);

}
