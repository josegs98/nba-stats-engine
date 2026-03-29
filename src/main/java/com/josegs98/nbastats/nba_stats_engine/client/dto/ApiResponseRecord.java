package com.josegs98.nbastats.nba_stats_engine.client.dto;

import java.util.List;

public record ApiResponseRecord<T>(List<T> data,
                                   MetaRecord meta) {
}
