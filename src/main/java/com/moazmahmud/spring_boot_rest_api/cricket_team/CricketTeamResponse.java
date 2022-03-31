package com.moazmahmud.spring_boot_rest_api.cricket_team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CricketTeamResponse {
    private Long id;
    private String name;
    private String nickName;
}
