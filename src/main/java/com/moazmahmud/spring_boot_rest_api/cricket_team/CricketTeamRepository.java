package com.moazmahmud.spring_boot_rest_api.cricket_team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CricketTeamRepository extends JpaRepository<CricketTeam, Long> {

    @Query("from CricketTeam cricketTeamjoin " +
           "left join fetch cricketTeamjoin.cricketPlayers " +
           "where cricketTeamjoin.id = :id")
    Optional<CricketTeam> getEntityWithPlayersById(@Param("id") Long id);
}