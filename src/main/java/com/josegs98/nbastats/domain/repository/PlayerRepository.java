package com.josegs98.nbastats.domain.repository;

import com.josegs98.nbastats.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName);
}
