package com.example.testspringboot.repository;

import com.example.testspringboot.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    boolean existsByTitle(String title);
}
