package com.moazmahmud.spring_boot_rest_api.cricket_player;

import com.moazmahmud.spring_boot_rest_api.cricket_team.CricketTeam;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cricket_team_id", foreignKey = @ForeignKey(name = "fk_cricket_team"))
    private CricketTeam cricketTeam;
}
