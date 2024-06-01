package com.example.testspringboot.dto;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActeurDto {
    private Long id;
    private String nom;
    private String prenom;
    private Set<FilmDto> filmDtos =  new HashSet<>();
}
