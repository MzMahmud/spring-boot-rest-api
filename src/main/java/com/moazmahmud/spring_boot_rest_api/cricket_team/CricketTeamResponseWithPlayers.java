package com.moazmahmud.spring_boot_rest_api.cricket_team;

import com.moazmahmud.spring_boot_rest_api.cricket_player.CricketPlayerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CricketTeamResponseWithPlayers {
    private Long id;
    private String name;
    private String nickName;
    private List<CricketPlayerResponse> cricketPlayerList;
}
