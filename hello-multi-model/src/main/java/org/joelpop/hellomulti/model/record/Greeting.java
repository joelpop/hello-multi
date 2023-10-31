package org.joelpop.hellomulti.model.record;

import java.time.Instant;

public record Greeting(
        String salutation,
        String name,
        Instant timestamp) {}
