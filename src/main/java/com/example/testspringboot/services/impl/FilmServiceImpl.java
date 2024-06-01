package com.example.testspringboot.services.impl;

import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.exception.FilmNotFoundException;
import com.example.testspringboot.mapper.FilmMapper;
import com.example.testspringboot.repository.FilmRepository;
import com.example.testspringboot.services.FilmService;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    private final FilmMapper filmMapper;

    public FilmServiceImpl(FilmMapper filmMapper, FilmRepository filmRepository) {
        this.filmMapper = filmMapper;
        this.filmRepository = filmRepository;
    }

    @Override
    public FilmDto getFilmById(long id) {
        Film film = filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("Film not found with id " + id));
        return filmMapper.toDto(film);
    }

    @Override
    public FilmDto addFilm(FilmDto filmDto) {
        Film newFilm = filmMapper.toEntity(filmDto);
        newFilm = filmRepository.save(newFilm);
        return filmMapper.toDto(newFilm);
    }
}
