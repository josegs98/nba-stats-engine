package com.josegs98.nbastats.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiPlayerResponse(
        Long id,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        String position,
        ApiTeamResponse team
) {

    public record ApiTeamResponse(
            Long id,
            @JsonProperty("full_name") String fullName,
            String abbreviation,
            String conference,
            String division,
            String city
    ) {}
}
