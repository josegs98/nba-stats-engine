package com.josegs98.nbastats.service;

import com.josegs98.nbastats.client.BallDontLieClient;
import com.josegs98.nbastats.client.dto.ApiStatsResponse;
import com.josegs98.nbastats.domain.model.GameStats;
import com.josegs98.nbastats.domain.model.Player;
import com.josegs98.nbastats.domain.repository.GameStatsRepository;
import com.josegs98.nbastats.domain.repository.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatsIngestionService {

    private final GameStatsRepository gameStatsRepository;
    private final PlayerRepository playerRepository;
    private final BallDontLieClient ballDontLieClient;

    public StatsIngestionService(GameStatsRepository gameStatsRepository,
                                 PlayerRepository playerRepository,
                                 BallDontLieClient ballDontLieClient) {
        this.gameStatsRepository = gameStatsRepository;
        this.playerRepository = playerRepository;
        this.ballDontLieClient = ballDontLieClient;
    }

    @Transactional
    public List<GameStats> getGameLogs(Long playerId, int season) {
        if (gameStatsRepository.existsByPlayerIdAndSeason(playerId, season)) {
            return gameStatsRepository.findByPlayerIdAndSeason(playerId, season);
        }

        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Player not found with ID: " + playerId + ". Search the player first."));

        var apiStats = ballDontLieClient.getPlayerStats(playerId, season);
        var savedStats = apiStats.stream()
                .filter(dto -> dto.game() != null && dto.game().date() != null)
                .map(dto -> mapAndPersistGameStats(dto, player))
                .toList();

        return savedStats;
    }

    private GameStats mapAndPersistGameStats(ApiStatsResponse dto, Player player) {
        var gameStats = new GameStats(
                player,
                dto.game().date(),
                dto.pts() != null ? dto.pts() : 0,
                dto.reb() != null ? dto.reb() : 0,
                dto.ast() != null ? dto.ast() : 0,
                dto.stl() != null ? dto.stl() : 0,
                dto.blk() != null ? dto.blk() : 0,
                dto.turnover() != null ? dto.turnover() : 0,
                dto.min(),
                dto.fgm() != null ? dto.fgm() : 0,
                dto.fga() != null ? dto.fga() : 0,
                dto.fg3m() != null ? dto.fg3m() : 0,
                dto.fg3a() != null ? dto.fg3a() : 0,
                dto.ftm() != null ? dto.ftm() : 0,
                dto.fta() != null ? dto.fta() : 0
        );
        return gameStatsRepository.save(gameStats);
    }
}
