package org.joelpop.hellomulti.jpaservice.map;

import org.joelpop.hellomulti.jpaclient.dto.GreetingDetailDto;
import org.joelpop.hellomulti.jpaclient.dto.GreetingSummaryDto;
import org.joelpop.hellomulti.jpaclient.entity.GreetingEntity;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import org.joelpop.hellomulti.shared.util.LibraryClass;

import java.util.Optional;

public final class GreetingMap extends LibraryClass {

    private GreetingMap() throws IllegalAccessException {
        // library class - not intended for instantiation
    }


    // DTO -> UI Model

    // GreetingSummaryDto
    public static Greeting map(GreetingSummaryDto greetingSummaryDto) {
        return Optional.ofNullable(greetingSummaryDto)
                .map(GreetingMap::mapFromSummaryDto)
                .orElse(null);
    }

    private static Greeting mapFromSummaryDto(GreetingSummaryDto greetingSummaryDto) {
        var greeting = new Greeting();

        greeting.setKey(greetingSummaryDto.getKey());
        greeting.setMessage(greetingSummaryDto.getMessage());

        return greeting;
    }

    // GreetingDetailDto
    public static Greeting map(GreetingDetailDto greetingDetailDto) {
        return Optional.ofNullable(greetingDetailDto)
                .map(GreetingMap::mapFromDetailDto)
                .orElse(null);
    }

    private static Greeting mapFromDetailDto(GreetingDetailDto greetingDetailDto) {
        var greeting = mapFromSummaryDto(greetingDetailDto);

        greeting.setName(greetingDetailDto.getName());
        greeting.setTimestamp(greetingDetailDto.getTimestamp());

        return greeting;
    }


    // UI Model -> Entity

    // Greeting
    public static GreetingEntity map(Greeting greeting) {
        return Optional.ofNullable(greeting)
                .map(GreetingMap::mapToEntity)
                .orElse(null);
    }

    private static GreetingEntity mapToEntity(Greeting greeting) {
        var greetingEntity = new GreetingEntity();

        greetingEntity.setKey(greeting.getKey());
        greetingEntity.setName(greeting.getName());
        greetingEntity.setMessage(greeting.getMessage());
        greetingEntity.setTimestamp(greeting.getTimestamp());

        return greetingEntity;
    }
}
