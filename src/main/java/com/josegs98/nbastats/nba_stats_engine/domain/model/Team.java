package com.josegs98.nbastats.nba_stats_engine.domain.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "teams")
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Team {

    @Id
    private Long id; // ID directo de la API, sin @GeneratedValue

    @NonNull
    @Column(nullable = false, unique = true)
    private String abbreviation; // "LAL"

    @NonNull
    @Column(nullable = false)
    private String city; // "Los Angeles"

    @NonNull
    @Column(nullable = false)
    private String name; // "Lakers"

    @NonNull
    @Column(name = "full_name", nullable = false)
    private String fullName; // "Los Angeles Lakers"

    private String conference; // "West"
    private String division;   // "Pacific"
}
