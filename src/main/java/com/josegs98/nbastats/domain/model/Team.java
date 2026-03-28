package com.josegs98.nbastats.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String abbreviation;

    @Column(nullable = false)
    private String conference;

    @Column(nullable = false)
    private String division;

    @Column(nullable = false)
    private String city;

    protected Team() {}

    public Team(Long id, String fullName, String abbreviation, String conference, String division, String city) {
        this.id = id;
        this.fullName = fullName;
        this.abbreviation = abbreviation;
        this.conference = conference;
        this.division = division;
        this.city = city;
    }

    public Long getId() { return id; }

    public String getFullName() { return fullName; }

    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getAbbreviation() { return abbreviation; }

    public void setAbbreviation(String abbreviation) { this.abbreviation = abbreviation; }

    public String getConference() { return conference; }

    public void setConference(String conference) { this.conference = conference; }

    public String getDivision() { return division; }

    public void setDivision(String division) { this.division = division; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    @Override
    public String toString() {
        return fullName + " (" + abbreviation + ")";
    }
}
