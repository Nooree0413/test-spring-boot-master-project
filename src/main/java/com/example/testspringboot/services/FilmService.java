package com.example.testspringboot.services;

import com.example.testspringboot.dto.FilmDto;

public interface FilmService {
    FilmDto getFilmById(long id);

    FilmDto addFilm(FilmDto filmDto);

    boolean isDuplicateFilmTitle(String title);
}
