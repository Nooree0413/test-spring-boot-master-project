package com.example.testspringboot.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDto {
    private Long id;
    private String title;
    private String description;
    private Set<ActeurDto> acteurDtos =  new HashSet<>();
}
