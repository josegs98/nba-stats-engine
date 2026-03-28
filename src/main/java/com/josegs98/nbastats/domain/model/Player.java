package com.josegs98.nbastats.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {

    @Id
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    protected Player() {}

    public Player(Long id, String firstName, String lastName, String position, Team team) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.team = team;
    }

    public Long getId() { return id; }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPosition() { return position; }

    public void setPosition(String position) { this.position = position; }

    public Team getTeam() { return team; }

    public void setTeam(Team team) { this.team = team; }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + id + ")";
    }
}
