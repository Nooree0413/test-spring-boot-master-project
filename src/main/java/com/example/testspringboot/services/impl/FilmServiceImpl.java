package com.example.testspringboot.services.impl;

import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.exception.DuplicateFilmException;
import com.example.testspringboot.exception.FilmNotFoundException;
import com.example.testspringboot.mapper.FilmMapper;
import com.example.testspringboot.repository.FilmRepository;
import com.example.testspringboot.services.FilmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public FilmDto addFilm(FilmDto filmDto) {
        if(isDuplicateFilmTitle(filmDto.getTitle())){
            throw new DuplicateFilmException("Film with title '" + filmDto.getTitle() + "' already exists.");
        }
        Film newFilm = filmMapper.toEntity(filmDto);
        newFilm = filmRepository.save(newFilm);
        return filmMapper.toDto(newFilm);
    }

    @Override
    public boolean isDuplicateFilmTitle(String title) {
        return filmRepository.existsByTitle(title);
    }
}
