package org.joelpop.hellomulti.mockservice;

import org.joelpop.hellomulti.model.record.Greeting;
import org.joelpop.hellomulti.service.GreetingService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class MockGreetingService implements GreetingService {
    @Override
    public Greeting getGreeting(String name) {
        return new Greeting("Hello",
                Optional.ofNullable(name)
                        .filter(Predicate.not(String::isBlank))
                        .orElse("Anonymous"),
                Instant.now());
    }
}
