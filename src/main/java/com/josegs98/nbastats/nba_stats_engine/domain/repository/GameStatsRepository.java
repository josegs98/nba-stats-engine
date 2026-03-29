package com.josegs98.nbastats.nba_stats_engine.domain.repository;

import com.josegs98.nbastats.nba_stats_engine.domain.model.GameStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameStatsRepository extends JpaRepository<GameStats, Long> {
    // Todos los game logs de un jugador (todas las temporadas)
    List<GameStats> findByPlayerId(Long playerId);

    // Game logs de un jugador filtrando por temporada
    List<GameStats> findByPlayerIdAndSeason(Long playerId, Integer season);

    // Lógica de caché: ¿ya tenemos datos de este jugador en esta temporada?
    boolean existsByPlayerIdAndSeason(Long playerId, Integer season);
}
