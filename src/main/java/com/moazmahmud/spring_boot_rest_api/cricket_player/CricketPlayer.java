package com.moazmahmud.spring_boot_rest_api.cricket_player;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cricket_player")
public class CricketPlayer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jersey_number")
    private String jerseyNumber;

    @Column(name = "cricket_player_types")
    @Convert(converter = CricketPlayerTypesConverter.class)
    private List<CricketPlayerType> cricketPlayerTypes;
}
