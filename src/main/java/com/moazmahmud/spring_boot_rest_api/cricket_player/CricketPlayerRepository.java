package com.moazmahmud.spring_boot_rest_api.cricket_player;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface CricketPlayerRepository extends JpaRepository<CricketPlayer, Long> {

    @Modifying
    @Query("update CricketPlayer cricketPlayer " +
           "set cricketPlayer.cricketTeam.id = :cricketTeamId " +
           "where cricketPlayer.id in (:cricketPlayerIds)")
    void updateCricketPlayerTeam(
            @Param("cricketPlayerIds") Collection<Long> cricketPlayerIds,
            @Param("cricketTeamId")Long cricketTeamId
    );
}