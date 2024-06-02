package com.example.testspringboot.services;

import com.example.testspringboot.entity.Film;

import java.util.Optional;

public interface FilmService {
    Optional<Film> getFilmById(long id);

    Film addFilm(Film film);

    boolean isDuplicateFilmTitle(String title);
}
