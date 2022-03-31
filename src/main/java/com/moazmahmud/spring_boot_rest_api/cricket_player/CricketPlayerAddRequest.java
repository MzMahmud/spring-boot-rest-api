package com.moazmahmud.spring_boot_rest_api.cricket_player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CricketPlayerAddRequest {
    private String name;
    private String jerseyNumber;
    private List<CricketPlayerType> cricketPlayerTypes;
}
