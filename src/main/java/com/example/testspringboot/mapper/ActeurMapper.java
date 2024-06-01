package com.example.testspringboot.mapper;

import com.example.testspringboot.dto.ActeurDto;
import com.example.testspringboot.entity.Acteur;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = FilmMapper.class)
public interface ActeurMapper {

    ActeurMapper INSTANCE = Mappers.getMapper(ActeurMapper.class);

    ActeurDto toDto(Acteur acteur);

    Acteur toEntity(ActeurDto acteurDto);
}
