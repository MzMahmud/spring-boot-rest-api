package com.moazmahmud.spring_boot_rest_api.cricket_team;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CricketTeamRepository extends JpaRepository<CricketTeam, Long> {
}