package org.joelpop.hellomulti.jpaclient.dto;

import java.time.Instant;

public interface GreetingDetailDto extends GreetingSummaryDto {

    String getName();
    Instant getTimestamp();
}
