package com.moazmahmud.spring_boot_rest_api.cricket_team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CricketTeamCricketPlayerAddRequest {
    private List<Long> cricketPlayerIds;
}
