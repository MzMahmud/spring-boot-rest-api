package com.moazmahmud.spring_boot_rest_api.cricket_player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CricketPlayerResponse {
    private Long id;
    private String name;
    private String jerseyNumber;
    private List<CricketPlayerType> cricketPlayerTypes;
}
