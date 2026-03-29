package com.josegs98.nbastats.nba_stats_engine.client;

import com.josegs98.nbastats.nba_stats_engine.client.dto.ApiResponseRecord;
import com.josegs98.nbastats.nba_stats_engine.client.dto.GameStatsRecord;
import com.josegs98.nbastats.nba_stats_engine.client.dto.PlayerRecord;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class BallDontLieClient {

    private final RestClient restClient;

    public BallDontLieClient(RestClient ballDontLieRestClient){
        this.restClient = ballDontLieRestClient;
    }

    public ApiResponseRecord<PlayerRecord> searchPlayers(String search) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/players")
                        .queryParam("search", search)
                        .queryParam("per_page", 25)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public ApiResponseRecord<GameStatsRecord> getPlayerStats(Long playerId, Integer season) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/stats")
                        .queryParam("player_ids[]", playerId)
                        .queryParam("seasons[]", season)
                        .queryParam("per_page", 100)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
