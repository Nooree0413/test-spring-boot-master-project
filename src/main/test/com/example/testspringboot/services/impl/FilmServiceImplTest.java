package com.example.testspringboot.services.impl;

import com.example.testspringboot.dto.ActeurDto;
import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Acteur;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.exception.FilmNotFoundException;
import com.example.testspringboot.mapper.FilmMapper;
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
    private FilmMapper filmMapper;

    private Film film;
    private FilmDto filmDto;

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

        ActeurDto acteurDto1 = ActeurDto.builder()
                .id(1L)
                .nom("nom 1")
                .prenom("prenom 1")
                .build();
        ActeurDto acteurDto2 = ActeurDto.builder()
                .id(1L)
                .nom("nom 2")
                .prenom("prenom 2")
                .build();

        Set<Acteur> acteurs = new HashSet<>(Set.of(acteur1, acteur2));
        Set<ActeurDto> acteurDtos = new HashSet<>(Set.of(acteurDto1, acteurDto2));


        film = Film.builder()
                .id(1L)
                .title("title 1")
                .description("description 1")
                .acteurs(acteurs)
                .build();

        filmDto = FilmDto.builder()
                .id(1L)
                .title("title 1")
                .description("description 1")
                .acteurDtos(acteurDtos)
                .build();

    }

    @Test
    void testGetFilmById()  {

        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(filmMapper.toDto(film)).thenReturn(filmDto);

        FilmDto result = filmService.getFilmById(1L);

        assertNotNull(result);
        assertEquals(filmDto.getTitle(), result.getTitle());
    }

    @Test
    void testGetFilmByIdNotFound() {
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FilmNotFoundException.class, () -> filmService.getFilmById(1L));
    }

    @Test
    void testCreateFilm() {

        when(filmMapper.toEntity(filmDto)).thenReturn(film);
        when(filmRepository.save(film)).thenReturn(film);
        when(filmMapper.toDto(film)).thenReturn(filmDto);

        FilmDto result = filmService.addFilm(filmDto);

        assertEquals("title 1", result.getTitle());
        verify(filmRepository, times(1)).save(film);
    }

}
