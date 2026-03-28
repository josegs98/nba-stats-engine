package com.josegs98.nbastats.client;

import com.josegs98.nbastats.client.dto.ApiPaginatedResponse;
import com.josegs98.nbastats.client.dto.ApiPlayerResponse;
import com.josegs98.nbastats.client.dto.ApiStatsResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class BallDontLieClient {

    private static final int PAGE_SIZE = 100;

    private final RestClient restClient;

    public BallDontLieClient(RestClient ballDontLieRestClient) {
        this.restClient = ballDontLieRestClient;
    }

    public List<ApiPlayerResponse> searchPlayers(String name) {
        var response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/players")
                        .queryParam("search", name)
                        .queryParam("per_page", PAGE_SIZE)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<ApiPaginatedResponse<ApiPlayerResponse>>() {});

        return response != null ? response.data() : List.of();
    }

    public List<ApiStatsResponse> getPlayerStats(Long playerId, int season) {
        List<ApiStatsResponse> allStats = new ArrayList<>();
        int page = 1;
        int totalPages;

        do {
            var finalPage = page;
            var response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/stats")
                            .queryParam("player_ids[]", playerId)
                            .queryParam("seasons[]", season)
                            .queryParam("per_page", PAGE_SIZE)
                            .queryParam("page", finalPage)
                            .build())
                    .retrieve()
                    .body(new ParameterizedTypeReference<ApiPaginatedResponse<ApiStatsResponse>>() {});

            if (response == null || response.data() == null) {
                break;
            }
            allStats.addAll(response.data());
            totalPages = response.meta() != null ? response.meta().totalPages() : 1;
            page++;
        } while (page <= totalPages);

        return allStats;
    }
}
