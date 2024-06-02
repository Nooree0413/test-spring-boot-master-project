package com.example.testspringboot.mapper;

import com.example.testspringboot.dto.FilmDto;
import com.example.testspringboot.entity.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = ActeurMapper.class)
public interface FilmMapper {
   FilmMapper INSTANCE = Mappers.getMapper(FilmMapper.class);

    @Mapping(source = "acteurs", target = "acteurDtos")
    FilmDto toDto(Film film);

    @Mapping(source = "acteurDtos", target = "acteurs")
    Film toEntity(FilmDto filmDto);
}
