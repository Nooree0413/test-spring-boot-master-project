package com.example.testspringboot.mapper;

import com.example.testspringboot.dto.ActeurDto;
import com.example.testspringboot.entity.Acteur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActeurMapper {

    ActeurDto toDto(Acteur acteur);

    Acteur toEntity(ActeurDto acteurDto);
}
