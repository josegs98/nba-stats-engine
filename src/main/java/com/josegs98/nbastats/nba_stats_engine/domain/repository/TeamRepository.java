package com.josegs98.nbastats.nba_stats_engine.domain.repository;

import com.josegs98.nbastats.nba_stats_engine.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    // Búsqueda por abreviatura (ej: "LAL", "GSW")
    Optional<Team> findByAbbreviationIgnoreCase(String abbreviation);
}
