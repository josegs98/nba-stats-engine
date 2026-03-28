package com.josegs98.nbastats.domain.repository;

import com.josegs98.nbastats.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
