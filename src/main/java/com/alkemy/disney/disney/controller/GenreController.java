package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.GenreDTO;
import com.alkemy.disney.disney.service.GenreService;
import com.alkemy.disney.disney.service.impl.GenreServiceImp;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll(){
        List<GenreDTO> genres = this.genreService.getAllGenres();
        return ResponseEntity.ok().body(genres);
    }

    @PostMapping
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO dto) {
        try{
            GenreDTO genreSaved = genreService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(genreSaved);
        }catch(ParamNotFouond e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

