package com.josegs98.nbastats.nba_stats_engine.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameRecord(Long id,
                         String date,
                         @JsonProperty("home_team_id") Long homeTeamId,
                         @JsonProperty("home_team_score") Integer homeTeamScore,
                         Boolean postseason,
                         Integer season,
                         String status,
                         @JsonProperty("visitor_team_id") Long visitorTeamId,
                         @JsonProperty("visitor_team_score") Integer visitorTeamScore) {
}
