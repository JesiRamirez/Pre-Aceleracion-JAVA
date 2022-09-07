package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharacterResponseDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping ("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;


    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character){
        try {
            CharacterDTO characterSaved = characterService.save(character);
            return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
        } catch (ParamNotFound e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO dto) throws ParamNotFound {
        CharacterDTO characterUpdated = characterService.update(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterUpdated);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO> getById(@PathVariable Long id){
        CharacterDTO characterDTO = characterService.getById(id);
        return ResponseEntity.ok().body(characterDTO);
    }

    @GetMapping("/{list}")
    public ResponseEntity<List<CharacterDTO>> getAll(){
        List<CharacterDTO> characters = characterService.getAllCharacter();
        return ResponseEntity.ok().body(characters);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<CharacterResponseDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) Set<Long> movies,
            @RequestParam(required = false, defaultValue="ASC") String order
    ){
        List<CharacterResponseDTO> characters = characterService.getByFilters(name, age, movies, order);
        return ResponseEntity.ok(characters);
    }

}
