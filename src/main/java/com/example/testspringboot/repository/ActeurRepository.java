package com.example.testspringboot.repository;

import com.example.testspringboot.entity.Acteur;
import com.example.testspringboot.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ActeurRepository extends JpaRepository<Acteur, Long> {
    Set<Acteur> findByFilmsContains(Film film);
}
