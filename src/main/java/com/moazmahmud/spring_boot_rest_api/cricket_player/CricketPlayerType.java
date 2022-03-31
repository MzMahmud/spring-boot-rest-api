package com.moazmahmud.spring_boot_rest_api.cricket_player;

import com.moazmahmud.spring_boot_rest_api.common.NotFoundException;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum CricketPlayerType {
    BATSMAN,
    BOWLER,
    WICKET_KEEPER,
    ALL_ROUNDER;

    private static final Map<String, CricketPlayerType> mapByName
            = Stream.of(values())
                    .collect(Collectors.toUnmodifiableMap(
                            Objects::toString,
                            Function.identity()
                    ));

    public static CricketPlayerType getByName(String name) {
        if(name == null || !mapByName.containsKey(name)) {
            throw new NotFoundException("No CricketPlayerType found with name=" + name);
        }
        return mapByName.get(name);
    }
}
