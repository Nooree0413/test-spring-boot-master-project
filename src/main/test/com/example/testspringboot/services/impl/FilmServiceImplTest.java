package com.example.testspringboot.services.impl;

import com.example.testspringboot.dto.ActeurDto;
import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Acteur;
import com.example.testspringboot.entity.Film;
import com.example.testspringboot.exception.FilmNotFoundException;
import com.example.testspringboot.mapper.FilmMapper;
import com.example.testspringboot.repository.FilmRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FilmServiceImplTest {
    @InjectMocks
    private FilmServiceImpl filmService;

    @Mock
    private FilmRepository filmRepository;

    @Mock
    private FilmMapper filmMapper;

    private Film film;
    private FilmDto filmDto;

    @Test
    public void testGetFilmById()  {
        Acteur acteur1 = Acteur.builder()
                .id(1l)
                .nom("nom 1")
                .prenom("prenom 1")
                .build();
        Acteur acteur2 = Acteur.builder()
                .id(1l)
                .nom("nom 2")
                .prenom("prenom 2")
                .build();

        ActeurDto acteurDto1 = ActeurDto.builder()
                .id(1l)
                .nom("nom 1")
                .prenom("prenom 1")
                .build();
        ActeurDto acteurDto2 = ActeurDto.builder()
                .id(1l)
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

        when(filmRepository.findById(1L)).thenReturn(Optional.of(film));
        when(filmMapper.toDto(film)).thenReturn(filmDto);

        FilmDto result = filmService.getFilmById(1L);

        assertNotNull(result);
        assertEquals(filmDto.getTitle(), result.getTitle());
    }

    @Test
    public void testGetFilmByIdNotFound() {
        when(filmRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(FilmNotFoundException.class, () -> filmService.getFilmById(1L));
    }
}
