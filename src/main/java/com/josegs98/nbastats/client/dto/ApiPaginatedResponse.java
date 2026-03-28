package com.josegs98.nbastats.client.dto;

import java.util.List;

public record ApiPaginatedResponse<T>(
        List<T> data,
        ApiMetaResponse meta
) {

    public record ApiMetaResponse(
            int page,
            @com.fasterxml.jackson.annotation.JsonProperty("per_page") int perPage,
            @com.fasterxml.jackson.annotation.JsonProperty("total_pages") int totalPages,
            @com.fasterxml.jackson.annotation.JsonProperty("total_count") int totalCount
    ) {}
}
