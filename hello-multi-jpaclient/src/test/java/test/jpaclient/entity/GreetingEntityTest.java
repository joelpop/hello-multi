package test.jpaclient.entity;

import org.instancio.Instancio;
import org.joelpop.hellomulti.jpaclient.entity.GreetingEntity;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;
import static test.util.AssertUtil.assertSetterHasNoSideEffects;

public class GreetingEntityTest {

    @Test
    void noArgInstantiation() {
        assertThatObject(new GreetingEntity())
                .isExactlyInstanceOf(GreetingEntity.class)
                .hasAllNullFieldsOrProperties();
    }

    @Test
    void keyGetterAndSetter() {
        assertSetterHasNoSideEffects("key",
                GreetingEntity::new, GreetingEntity::getKey, GreetingEntity::setKey,
                Stream.concat(Stream.of(null, 0L, 1L, null),
                                Instancio.ofList(Long.class).create().stream())
                        .toArray(Long[]::new));
    }

    @Test
    void nameGetterAndSetter() {
        assertSetterHasNoSideEffects("name",
                GreetingEntity::new, GreetingEntity::getName, GreetingEntity::setName,
                Stream.concat(Stream.of(null, "", "World", null),
                                Instancio.ofList(String.class).create().stream())
                        .toArray(String[]::new));
    }

    @Test
    void messageGetterAndSetter() {
        assertSetterHasNoSideEffects("message",
                GreetingEntity::new, GreetingEntity::getMessage, GreetingEntity::setMessage,
                Stream.concat(Stream.of(null, "", "Message", null),
                                Instancio.ofList(String.class).create().stream())
                        .toArray(String[]::new));
    }

    @Test
    void timestampGetterAndSetter() {
        assertSetterHasNoSideEffects("timestamp",
                GreetingEntity::new, GreetingEntity::getTimestamp, GreetingEntity::setTimestamp,
                Stream.concat(Stream.of(null, Instant.EPOCH, Instant.now(), null),
                                Instancio.ofList(Instant.class).create().stream())
                        .toArray(Instant[]::new));
    }

    @Test
    void equalsAndHashCode() {
        var greeting1 = new GreetingEntity();

        var greeting2 = new GreetingEntity();

        var greeting3 = new GreetingEntity();
        greeting3.setKey(1L);

        var greeting4 = new GreetingEntity();
        greeting4.setKey(1L);
        greeting4.setName("World");
        greeting4.setMessage("Hello, World!");
        greeting4.setTimestamp(Instant.EPOCH);

        var greeting5 = new GreetingEntity();
        greeting5.setName("World");
        greeting5.setMessage("Hello, World!");
        greeting5.setTimestamp(Instant.EPOCH);

        var greeting6 = new GreetingEntity();
        greeting6.setName("World");
        greeting6.setMessage("Hello, World!");
        greeting6.setTimestamp(Instant.EPOCH);

        assertThat(greeting1)
                .isEqualTo(greeting2)
                .hasSameHashCodeAs(greeting2)
                .isNotEqualTo(greeting3)
                .isNotEqualTo(greeting4)
                .isNotEqualTo(greeting5);

        assertThat(greeting2)
                .isEqualTo(greeting1)
                .hasSameHashCodeAs(greeting1)
                .isNotEqualTo(greeting3)
                .isNotEqualTo(greeting4)
                .isNotEqualTo(greeting5)
                .isNotEqualTo(greeting6);

        assertThat(greeting3)
                .isNotEqualTo(greeting1)
                .isNotEqualTo(greeting2)
                .isNotEqualTo(greeting4)
                .isNotEqualTo(greeting5)
                .isNotEqualTo(greeting6);

        assertThat(greeting4)
                .isNotEqualTo(greeting1)
                .isNotEqualTo(greeting2)
                .isNotEqualTo(greeting3)
                .isNotEqualTo(greeting5)
                .isNotEqualTo(greeting6);

        assertThat(greeting5)
                .isNotEqualTo(greeting1)
                .isNotEqualTo(greeting2)
                .isNotEqualTo(greeting3)
                .isNotEqualTo(greeting4)
                .isEqualTo(greeting6)
                .hasSameHashCodeAs(greeting6);

        assertThat(greeting6)
                .isNotEqualTo(greeting1)
                .isNotEqualTo(greeting2)
                .isNotEqualTo(greeting3)
                .isNotEqualTo(greeting4)
                .isEqualTo(greeting5)
                .hasSameHashCodeAs(greeting5);
    }
}
