package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.CharacterResponseDTO;
import com.alkemy.disney.disney.dto.CharacterDTO;
import com.alkemy.disney.disney.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping ("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;


    @PostMapping
    public ResponseEntity<CharacterDTO>save(@Valid @RequestBody CharacterDTO character) {
        CharacterDTO characterSaved = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterSaved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        characterService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO>update(@PathVariable Long id, @Valid @RequestBody CharacterDTO dto)  {
        CharacterDTO characterUpdated = characterService.update(id, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(characterUpdated);

    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDTO>getById(@PathVariable Long id){
        CharacterDTO characterDTO = characterService.getById(id);
        return ResponseEntity.ok(characterDTO);
    }

    @GetMapping("/{list}")
    public ResponseEntity<List<CharacterDTO>>getAll(){
        List<CharacterDTO> characters = characterService.getAllCharacter();
        return ResponseEntity.ok().body(characters);
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



