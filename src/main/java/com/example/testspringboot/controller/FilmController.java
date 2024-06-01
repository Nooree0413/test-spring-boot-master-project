package com.example.testspringboot.controller;

import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.services.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/film")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> getFilmById(@PathVariable Long id) {
        FilmDto filmDto = filmService.getFilmById(id);
        return new ResponseEntity<>(filmDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmDto filmDto) {
        FilmDto newFilm = filmService.addFilm(filmDto);
        return new ResponseEntity<>(newFilm, HttpStatus.CREATED);
    }
}
