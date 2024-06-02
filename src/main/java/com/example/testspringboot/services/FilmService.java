package com.example.testspringboot.services;

import com.example.testspringboot.entity.Film;

public interface FilmService {
    Film getFilmById(long id);

    Film addFilm(Film film);

    boolean isDuplicateFilmTitle(String title);
}
