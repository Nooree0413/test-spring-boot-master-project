package com.example.testspringboot.mapper;

import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ActeurMapper.class)
public interface FilmMapper {
    FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    FilmDto toDto(Film film);

    Film toEntity(FilmDto filmDto);
}
