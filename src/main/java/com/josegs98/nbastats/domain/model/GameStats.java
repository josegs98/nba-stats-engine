package com.josegs98.nbastats.domain.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "game_stats")
public class GameStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "game_date", nullable = false)
    private LocalDate gameDate;

    private int points;
    private int rebounds;
    private int assists;
    private int steals;
    private int blocks;
    private int turnovers;

    @Column(name = "minutes_played")
    private String minutesPlayed;

    private int fgm;
    private int fga;
    private int fg3m;
    private int fg3a;
    private int ftm;
    private int fta;

    protected GameStats() {}

    public GameStats(Player player, LocalDate gameDate, int points, int rebounds, int assists,
                     int steals, int blocks, int turnovers, String minutesPlayed,
                     int fgm, int fga, int fg3m, int fg3a, int ftm, int fta) {
        this.player = player;
        this.gameDate = gameDate;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
        this.turnovers = turnovers;
        this.minutesPlayed = minutesPlayed;
        this.fgm = fgm;
        this.fga = fga;
        this.fg3m = fg3m;
        this.fg3a = fg3a;
        this.ftm = ftm;
        this.fta = fta;
    }

    public Long getId() { return id; }

    public Player getPlayer() { return player; }

    public LocalDate getGameDate() { return gameDate; }

    public void setGameDate(LocalDate gameDate) { this.gameDate = gameDate; }

    public int getPoints() { return points; }

    public void setPoints(int points) { this.points = points; }

    public int getRebounds() { return rebounds; }

    public void setRebounds(int rebounds) { this.rebounds = rebounds; }

    public int getAssists() { return assists; }

    public void setAssists(int assists) { this.assists = assists; }

    public int getSteals() { return steals; }

    public void setSteals(int steals) { this.steals = steals; }

    public int getBlocks() { return blocks; }

    public void setBlocks(int blocks) { this.blocks = blocks; }

    public int getTurnovers() { return turnovers; }

    public void setTurnovers(int turnovers) { this.turnovers = turnovers; }

    public String getMinutesPlayed() { return minutesPlayed; }

    public void setMinutesPlayed(String minutesPlayed) { this.minutesPlayed = minutesPlayed; }

    public int getFgm() { return fgm; }

    public void setFgm(int fgm) { this.fgm = fgm; }

    public int getFga() { return fga; }

    public void setFga(int fga) { this.fga = fga; }

    public int getFg3m() { return fg3m; }

    public void setFg3m(int fg3m) { this.fg3m = fg3m; }

    public int getFg3a() { return fg3a; }

    public void setFg3a(int fg3a) { this.fg3a = fg3a; }

    public int getFtm() { return ftm; }

    public void setFtm(int ftm) { this.ftm = ftm; }

    public int getFta() { return fta; }

    public void setFta(int fta) { this.fta = fta; }
}
