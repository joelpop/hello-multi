package org.joelpop.hellomulti.service;

import org.joelpop.hellomulti.model.record.Greeting;

public interface GreetingService {

    Greeting getGreeting(String name);

}
