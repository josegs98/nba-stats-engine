package com.josegs98.nbastats.service;

import com.josegs98.nbastats.client.BallDontLieClient;
import com.josegs98.nbastats.client.dto.ApiPlayerResponse;
import com.josegs98.nbastats.domain.model.Player;
import com.josegs98.nbastats.domain.model.Team;
import com.josegs98.nbastats.domain.repository.PlayerRepository;
import com.josegs98.nbastats.domain.repository.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final BallDontLieClient ballDontLieClient;

    public PlayerService(PlayerRepository playerRepository,
                         TeamRepository teamRepository,
                         BallDontLieClient ballDontLieClient) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.ballDontLieClient = ballDontLieClient;
    }

    @Transactional
    public List<Player> searchPlayers(String name) {
        var localResults = playerRepository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);

        if (!localResults.isEmpty()) {
            return localResults;
        }

        var apiResults = ballDontLieClient.searchPlayers(name);
        return apiResults.stream()
                .map(this::mapAndPersistPlayer)
                .toList();
    }

    @Transactional
    public Player getOrFetchPlayer(Long playerId) {
        return playerRepository.findById(playerId)
                .orElseThrow(() -> new IllegalArgumentException("Player not found with ID: " + playerId));
    }

    private Player mapAndPersistPlayer(ApiPlayerResponse dto) {
        return playerRepository.findById(dto.id())
                .orElseGet(() -> {
                    Team team = null;
                    if (dto.team() != null) {
                        team = teamRepository.findById(dto.team().id())
                                .orElseGet(() -> teamRepository.save(new Team(
                                        dto.team().id(),
                                        dto.team().fullName(),
                                        dto.team().abbreviation(),
                                        dto.team().conference(),
                                        dto.team().division(),
                                        dto.team().city()
                                )));
                    }
                    var player = new Player(dto.id(), dto.firstName(), dto.lastName(), dto.position(), team);
                    return playerRepository.save(player);
                });
    }
}
