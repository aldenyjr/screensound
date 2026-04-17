package com.github.aldenyjr.screensound.repository;

import com.github.aldenyjr.screensound.models.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nome);
    Optional<Artista> findByNomeIgnoreCase(String nome);
}
