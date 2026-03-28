package com.josegs98.nbastats.domain.repository;

import com.josegs98.nbastats.domain.model.GameStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameStatsRepository extends JpaRepository<GameStats, Long> {

    @Query("SELECT g FROM GameStats g WHERE g.player.id = :playerId AND EXTRACT(YEAR FROM g.gameDate) = :season")
    List<GameStats> findByPlayerIdAndSeason(@Param("playerId") Long playerId, @Param("season") int season);

    @Query("SELECT COUNT(g) > 0 FROM GameStats g WHERE g.player.id = :playerId AND EXTRACT(YEAR FROM g.gameDate) = :season")
    boolean existsByPlayerIdAndSeason(@Param("playerId") Long playerId, @Param("season") int season);
}
