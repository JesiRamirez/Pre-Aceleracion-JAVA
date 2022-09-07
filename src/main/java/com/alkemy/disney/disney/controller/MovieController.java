package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.MovieResponseDTO;
import com.alkemy.disney.disney.dto.MovieDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO dto) {
        try {
            MovieDTO movieSaved = movieService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
        }catch (ParamNotFound e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getById(@PathVariable("id") Long id){
        MovieDTO movieDTO = movieService.getById(id);
        return ResponseEntity.ok().body(movieDTO);
    }

    @GetMapping("/list")
    public ResponseEntity<List<MovieDTO>> getAll(){
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }


    @PostMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable("id") Long id, @RequestBody MovieDTO dto) {
        try{
            MovieDTO movieUpdated = movieService.update(id, dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(movieUpdated);
        }catch(ParamNotFound e){
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
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

    @PostMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> addCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter){
        try{
            movieService.addCharacter(idMovie, idCharacter);
        }catch (ParamNotFound e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{idMovie}/characters/{idCharacter}")
    public ResponseEntity<Void> removeCharacter(@PathVariable Long idMovie, @PathVariable Long idCharacter){
        try{
            movieService.removeCharacter(idMovie, idCharacter);
        }catch (ParamNotFound e){
            throw new RuntimeException(e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
