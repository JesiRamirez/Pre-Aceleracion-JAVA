package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.MovieResponseDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> save(@Valid @RequestBody MovieDTO dto)  {
        MovieDTO movieSaved = movieService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter) {
        movieService.addCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> removeCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter){
        movieService.removeCharacter(idMovie, idCharacter);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @Valid @RequestParam MovieDTO dto){
        MovieDTO dtoUpdated = movieService.update(id, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dtoUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getById(@PathVariable Long id) {
        MovieDTO movieDTO = movieService.getById(id);
        return ResponseEntity.ok(movieDTO);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponseDTO>> getByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ){
        List<MovieResponseDTO> movies = movieService.getByFilters(name, genre, order);
        return ResponseEntity.ok(movies);
    }








}
