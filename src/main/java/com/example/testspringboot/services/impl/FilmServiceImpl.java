package com.example.testspringboot.services.impl;

import com.example.testspringboot.entity.Acteur;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.exception.DuplicateFilmException;
import com.example.testspringboot.repository.ActeurRepository;
import com.example.testspringboot.repository.FilmRepository;
import com.example.testspringboot.services.FilmService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
public class FilmServiceImpl implements FilmService {
    private final FilmRepository filmRepository;
    private final ActeurRepository acteurRepository;

    public FilmServiceImpl(FilmRepository filmRepository, ActeurRepository acteurRepository) {
        this.filmRepository = filmRepository;
        this.acteurRepository = acteurRepository;
    }

    @Override
    public Optional<Film> getFilmById(long id) {
        Optional<Film> optionalFilm = filmRepository.findById(id);
        optionalFilm.ifPresent(film -> {
            Set<Acteur> acteurs = acteurRepository.findByFilmsContains(film);
            film.setActeurs(acteurs);
        });
        return optionalFilm;

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
