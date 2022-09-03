package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.MovieDTO;
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

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAll(){
        List<MovieDTO> movies = this.movieService.getAllMovies();
        return ResponseEntity.ok().body(movies);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO dto) {
        MovieDTO movieSaved = movieService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getById(@PathVariable Long id){
        MovieDTO movieDTO = movieService.getById(id);
        return ResponseEntity.ok().body(movieDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO dto) {
        MovieDTO movieUpdated = movieService.update(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieUpdated);
    }

    //@DeleteMapping("/{id}")

    //@GetMapping

    //@PostMapping("/{idMovie}/characters/{idCharacter}")



}
