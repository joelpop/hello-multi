package org.joelpop.hellomulti.uimodel.service;

import org.joelpop.hellomulti.uimodel.model.Greeting;

import java.util.List;

public interface GreetingService {

    Greeting generateAndLog(String name);

    List<Greeting> fetchAll();

    void delete(Greeting greeting);

    void clear();
}
