package com.josegs98.nbastats.nba_stats_engine.domain.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "game_stats")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GameStats {

    @Id
    private Long id; // ID del stat en la API

    @NonNull
    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @NonNull
    @Column(name = "game_date", nullable = false)
    private LocalDate gameDate;

    @NonNull
    @Column(nullable = false)
    private Integer season; // temporada, ej: 2024

    private String min;  // minutos jugados, ej: "36:42"

    // Estadísticas del partido
    private Integer pts;
    private Integer ast;
    private Integer reb;
    private Integer oreb;
    private Integer dreb;
    private Integer stl;
    private Integer blk;
    private Integer turnover; // "to" es palabra reservada en Java
    private Integer pf;

    // Tiros de campo
    private Integer fgm;
    private Integer fga;
    @Column(name = "fg_pct")
    private Double fgPct;

    // Triples
    private Integer fg3m;
    private Integer fg3a;
    @Column(name = "fg3_pct")
    private Double fg3Pct;

    // Tiros libres
    private Integer ftm;
    private Integer fta;
    @Column(name = "ft_pct")
    private Double ftPct;

    // Contexto del partido
    @ManyToOne
    @JoinColumn(name = "home_team_id")
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "visitor_team_id")
    private Team visitorTeam;

    @Column(name = "home_team_score")
    private Integer homeTeamScore;

    @Column(name = "visitor_team_score")
    private Integer visitorTeamScore;

    private Boolean postseason;
}
