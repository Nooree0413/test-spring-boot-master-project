package com.example.testspringboot.services.impl;

import com.example.testspringboot.entity.Acteur;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.repository.ActeurRepository;
import com.example.testspringboot.repository.FilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
 class FilmServiceImplTest {
    @InjectMocks
    private FilmServiceImpl filmService;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private ActeurRepository acteurRepository;

    private Film film;

    Set<Acteur> acteurs = new HashSet<>();

    @BeforeEach
    public void setUp() {
        Acteur acteur1 = Acteur.builder()
                .id(1L)
                .nom("nom 1")
                .prenom("prenom 1")
                .build();
        Acteur acteur2 = Acteur.builder()
                .id(1L)
                .nom("nom 2")
                .prenom("prenom 2")
                .build();

       acteurs.add(acteur1);
       acteurs.add(acteur2);

        film = Film.builder()
                .id(1L)
                .title("title 1")
                .description("description 1")
                .acteurs(acteurs)
                .build();



    }

    @Test
    void testGetFilmById()  {
        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(acteurRepository.findByFilmsContains(film)).thenReturn(acteurs);

        Optional<Film> result = filmService.getFilmById(1L);

        assertTrue(result.isPresent());
        assertEquals(film.getId(), result.get().getId());
        assertEquals(film.getTitle(), result.get().getTitle());
        assertEquals(acteurs, result.get().getActeurs());
    }

    @Test
    void testGetFilmByIdNotFound() {
        when(filmRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Film> result = filmService.getFilmById(2L);

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateFilm() {

        when(filmRepository.save(film)).thenReturn(film);

        Film result = filmService.addFilm(film);

        assertEquals("title 1", result.getTitle());
        verify(filmRepository, times(1)).save(film);
    }

}
