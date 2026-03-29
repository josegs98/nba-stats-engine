package com.josegs98.nbastats.nba_stats_engine.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MetaRecord(@JsonProperty("next_cursor") Integer nextCursor,
                         @JsonProperty("per_page") Integer perPage) {
}
