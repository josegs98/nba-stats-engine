package com.josegs98.nbastats.nba_stats_engine.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "players")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Player {

    @Id
    private Long id; // ID directo de la API

    @NonNull
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NonNull
    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String position;    // "G", "F", "C", "G-F"...
    private String height;      // "6-9" (formato de la API)
    private String weight;      // "250"

    @Column(name = "jersey_number")
    private String jerseyNumber;

    private String college;
    private String country;

    @Column(name = "draft_year")
    private Integer draftYear;

    @Column(name = "draft_round")
    private Integer draftRound;

    @Column(name = "draft_number")
    private Integer draftNumber;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
