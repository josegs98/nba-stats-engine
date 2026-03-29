package com.josegs98.nbastats.nba_stats_engine.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameStatsRecord(Long id,
                              Integer ast,
                              Integer blk,
                              Integer dreb,
                              @JsonProperty("fg3_pct") Double fg3Pct,
                              Integer fg3a,
                              Integer fg3m,
                              @JsonProperty("fg_pct") Double fgPct,
                              Integer fga,
                              Integer fgm,
                              @JsonProperty("ft_pct") Double ftPct,
                              Integer fta,
                              Integer ftm,
                              GameRecord game,
                              String min,
                              Integer oreb,
                              Integer pf,
                              PlayerRecord player,
                              Integer pts,
                              Integer reb,
                              Integer stl,
                              TeamRecord team,
                              Integer turnover) {
}
