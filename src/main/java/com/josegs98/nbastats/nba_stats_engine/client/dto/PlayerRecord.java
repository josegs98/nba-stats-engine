package com.josegs98.nbastats.nba_stats_engine.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlayerRecord(Long id,
                           @JsonProperty("first_name") String firstName,
                           @JsonProperty("last_name") String lastName,
                           String position,
                           String height,
                           String weight,
                           @JsonProperty("jersey_number") String jerseyNumber,
                           String college,
                           String country,
                           @JsonProperty("draft_year") Integer draftYear,
                           @JsonProperty("draft_round") Integer draftRound,
                           @JsonProperty("draft_number") Integer draftNumber,
                           TeamRecord team){
}
