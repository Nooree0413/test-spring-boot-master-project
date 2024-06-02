package com.example.testspringboot.services.impl;

import com.example.testspringboot.entity.Film;
import com.example.testspringboot.exception.DuplicateFilmException;
import com.example.testspringboot.exception.FilmNotFoundException;
import com.example.testspringboot.repository.FilmRepository;
import com.example.testspringboot.services.FilmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;

    public FilmServiceImpl(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    @Override
    public Film getFilmById(long id) {
       return filmRepository.findById(id)
                .orElseThrow(() -> new FilmNotFoundException("Film not found with id " + id));

    }

    @Override
    @Transactional
    public Film addFilm(Film film) {
        if(isDuplicateFilmTitle(film.getTitle())){
            throw new DuplicateFilmException("Film with title '" + film.getTitle() + "' already exists.");
        }
        return filmRepository.save(film);

    }

    @Override
    public boolean isDuplicateFilmTitle(String title) {
        return filmRepository.existsByTitle(title);
    }
}
