package com.josegs98.nbastats.nba_stats_engine.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TeamRecord (Long id,
                          String abbreviation,
                          String city,
                          String name,
                          @JsonProperty("full_name") String fullName,
                          String conference,
                          String division) {
}
