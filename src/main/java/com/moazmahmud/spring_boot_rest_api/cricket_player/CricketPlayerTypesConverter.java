package com.moazmahmud.spring_boot_rest_api.cricket_player;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Converter
public class CricketPlayerTypesConverter implements AttributeConverter<List<CricketPlayerType>, String> {
    @Override
    public String convertToDatabaseColumn(List<CricketPlayerType> cricketPlayerTypes) {
        if (cricketPlayerTypes == null) {
            return null;
        }
        return cricketPlayerTypes
                .stream()
                .map(Objects::toString)
                .collect(Collectors.joining(","));
    }

    @Override
    public List<CricketPlayerType> convertToEntityAttribute(String s) {
        if (s == null) {
            return new ArrayList<>();
        }
        return Stream.of(s.split(","))
                     .map(CricketPlayerType::getByName)
                     .collect(Collectors.toList());
    }
}
