package org.joelpop.hellomulti.jpaclient.repo;

import org.joelpop.hellomulti.jpaclient.dto.GreetingDetailDto;
import org.joelpop.hellomulti.jpaclient.dto.GreetingSummaryDto;
import org.joelpop.hellomulti.jpaclient.entity.GreetingEntity;

import java.util.List;

public interface GreetingRepository extends LongKeyRepository<GreetingEntity> {

    List<GreetingDetailDto> findAllByOrderByTimestampDesc();

    GreetingSummaryDto saveAndFlush(GreetingEntity greetingEntity);

    void delete(GreetingEntity greetingEntity);

    void deleteAll();
}
