package org.joelpop.hellomulti.mockservice;

import org.joelpop.hellomulti.uimodel.service.GreetingService;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This module/class is still a work in progress...
 */
@Service
@Primary
public class MockGreetingService implements GreetingService {

    @Override
    public Greeting generateAndLog(String name) {
        // default the name to "World"
        var message = "Hello, " + (name.isEmpty() ? "World" : name) + "!";

        var greeting = new Greeting();
        greeting.setName(name);
        greeting.setMessage(message);

        return greeting;
    }

    @Override
    public List<Greeting> fetchAll() {
        return List.of();
    }

    @Override
    public void delete(Greeting greeting) {
        // TODO implement MockGreetingService.delete()
    }

    @Override
    public void clear() {
        // TODO implement MockGreetingService.clear()
    }
}
