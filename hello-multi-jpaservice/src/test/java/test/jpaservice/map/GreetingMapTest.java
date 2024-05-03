package test.jpaservice.map;

import org.assertj.core.api.Assertions;
import org.instancio.Instancio;
import org.joelpop.hellomulti.jpaclient.dto.GreetingDetailDto;
import org.joelpop.hellomulti.jpaclient.dto.GreetingSummaryDto;
import org.joelpop.hellomulti.jpaclient.entity.GreetingEntity;
import org.joelpop.hellomulti.jpaservice.map.GreetingMap;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import org.testng.annotations.Test;

import static test.util.AssertUtil.propertyIsEqualTo;
import static test.util.AssertUtil.propertyIsNull;

public class GreetingMapTest {

    @Test
    void mapGreetingSummaryDtoToGreetingTest() {
        GreetingSummaryDto greetingSummaryDto = Instancio.create(GreetingEntity.class);

        var greeting = GreetingMap.map(greetingSummaryDto);
        Assertions.assertThat(greeting)
                .has(propertyIsEqualTo(Greeting::getKey, greetingSummaryDto.getKey(), "key"))
                .has(propertyIsNull(Greeting::getName, "name"))
                .has(propertyIsEqualTo(Greeting::getMessage, greetingSummaryDto.getMessage(), "message"))
                .has(propertyIsNull(Greeting::getTimestamp, "timestamp"));
    }

    @Test
    void mapGreetingDetailDtoToGreetingTest() {
        GreetingDetailDto greetingDetailDto = Instancio.create(GreetingEntity.class);

        var greeting = GreetingMap.map(greetingDetailDto);
        Assertions.assertThat(greeting)
                .has(propertyIsEqualTo(Greeting::getKey, greetingDetailDto.getKey(), "key"))
                .has(propertyIsEqualTo(Greeting::getName, greetingDetailDto.getName(), "name"))
                .has(propertyIsEqualTo(Greeting::getMessage, greetingDetailDto.getMessage(), "message"))
                .has(propertyIsEqualTo(Greeting::getTimestamp, greetingDetailDto.getTimestamp(), "timestamp"));
    }

    @Test
    void mapGreetingToGreetingEntityTest() {
        Greeting greeting = Instancio.create(Greeting.class);

        var greetingEntity = GreetingMap.map(greeting);
        Assertions.assertThat(greetingEntity)
                .has(propertyIsEqualTo(GreetingEntity::getKey, greeting.getKey(), "key"))
                .has(propertyIsEqualTo(GreetingEntity::getName, greeting.getName(), "name"))
                .has(propertyIsEqualTo(GreetingEntity::getMessage, greeting.getMessage(), "message"))
                .has(propertyIsEqualTo(GreetingEntity::getTimestamp, greeting.getTimestamp(), "timestamp"));
    }

}
