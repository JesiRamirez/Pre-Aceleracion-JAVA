package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.exception.ParamNotFound;
import com.alkemy.disney.disney.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO dto) throws ParamNotFound {
        GenreDTO genreSaved = genreService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);
    }

}

