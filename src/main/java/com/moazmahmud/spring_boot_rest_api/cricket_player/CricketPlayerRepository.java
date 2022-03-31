package com.moazmahmud.spring_boot_rest_api.cricket_player;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CricketPlayerRepository extends JpaRepository<CricketPlayer, Long> {
}