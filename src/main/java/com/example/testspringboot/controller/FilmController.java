package com.example.testspringboot.controller;

import com.example.testspringboot.entity.Film;
import com.example.testspringboot.services.FilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/film")
public class FilmController {
    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Long id) {
        Optional<Film> film = filmService.getFilmById(id);
        return new ResponseEntity<>(film.orElse(null), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        Film newFilm = filmService.addFilm(film);
        return new ResponseEntity<>(newFilm, HttpStatus.CREATED);
    }
}
