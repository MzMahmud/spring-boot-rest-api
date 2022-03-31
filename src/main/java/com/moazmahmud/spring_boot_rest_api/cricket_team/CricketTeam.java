package com.moazmahmud.spring_boot_rest_api.cricket_team;

import com.moazmahmud.spring_boot_rest_api.cricket_player.CricketPlayer;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cricket_team")
public class CricketTeam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nick_name")
    private String nickName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cricketTeam")
    private Set<CricketPlayer> cricketPlayers = new HashSet<>();
}
