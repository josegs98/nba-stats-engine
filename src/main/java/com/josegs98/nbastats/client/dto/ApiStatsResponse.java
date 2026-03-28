package com.josegs98.nbastats.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ApiStatsResponse(
        Long id,
        @JsonProperty("player_id") Long playerId,
        @JsonProperty("game") ApiGameResponse game,
        Integer pts,
        Integer reb,
        Integer ast,
        Integer stl,
        Integer blk,
        Integer turnover,
        String min,
        Integer fgm,
        Integer fga,
        @JsonProperty("fg3m") Integer fg3m,
        @JsonProperty("fg3a") Integer fg3a,
        Integer ftm,
        Integer fta
) {

    public record ApiGameResponse(
            Long id,
            LocalDate date
    ) {}
}
