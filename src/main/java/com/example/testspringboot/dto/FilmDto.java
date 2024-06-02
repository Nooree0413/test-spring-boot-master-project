package com.example.testspringboot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
    private Long id;
    private String title;
    private String description;
    private Set<ActeurDto> acteurDtos;
}
