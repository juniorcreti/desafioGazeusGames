package com.games.gazeus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.games.gazeus.domain.Match;
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {

}
