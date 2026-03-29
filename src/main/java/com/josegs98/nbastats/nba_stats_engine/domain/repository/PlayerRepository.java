package com.josegs98.nbastats.nba_stats_engine.domain.repository;

import com.josegs98.nbastats.nba_stats_engine.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    // Búsqueda por apellido (ej: el usuario escribe "James")
    List<Player> findByLastNameContainingIgnoreCase(String lastName);

    // Búsqueda combinada nombre + apellido (ej: "LeBron James")
    List<Player> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
            String firstName, String lastName);

    // Coincidencia exacta — evitar duplicados al persistir desde la API
    Optional<Player> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(
            String firstName, String lastName);
}
