package test.uimodel.model;

import org.instancio.Instancio;
import org.joelpop.hellomulti.uimodel.model.Greeting;
import org.testng.annotations.Test;

import java.time.Instant;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatObject;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static test.util.AssertUtil.assertSetterHasNoSideEffects;

public class GreetingTest {

    @Test
    void noArgInstantiation() {
        assertThatObject(new Greeting())
                .isExactlyInstanceOf(Greeting.class)
                .hasAllNullFieldsOrProperties();
    }

    @Test
    void keyGetterAndSetter() {
        assertSetterHasNoSideEffects("key",
                Greeting::new, Greeting::getKey, Greeting::setKey,
                Stream.concat(Stream.of(null, 0L, 1L, null),
                                Instancio.ofList(Long.class).create().stream())
                        .toArray(Long[]::new));
    }

    @Test
    void nameGetterAndSetter() {
        assertSetterHasNoSideEffects("name",
                Greeting::new, Greeting::getName, Greeting::setName,
                Stream.concat(Stream.of(null, "", "World", null),
                                Instancio.ofList(String.class).create().stream())
                        .toArray(String[]::new));
    }

    @Test
    void messageGetterAndSetter() {
        assertSetterHasNoSideEffects("message",
                Greeting::new, Greeting::getMessage, Greeting::setMessage,
                Stream.concat(Stream.of(null, "", "Message", null),
                                Instancio.ofList(String.class).create().stream())
                        .toArray(String[]::new));
    }

    @Test
    void timestampGetterAndSetter() {
        assertSetterHasNoSideEffects("timestamp",
                Greeting::new, Greeting::getTimestamp, Greeting::setTimestamp,
                Stream.concat(Stream.of(null, Instant.EPOCH, Instant.now(), null),
                                Instancio.ofList(Instant.class).create().stream())
                        .toArray(Instant[]::new));
    }

    @Test
    void equalsAndHashCode() {
        var greeting1 = new Greeting();

        var greeting2 = new Greeting();

        var greeting3 = new Greeting();
        greeting3.setKey(1L);

        var greeting4 = new Greeting();
        greeting4.setKey(1L);
        greeting4.setName("World");
        greeting4.setMessage("Hello, World!");
        greeting4.setTimestamp(Instant.EPOCH);

        var greeting5 = new Greeting();
        greeting5.setName("World");
        greeting5.setMessage("Hello, World!");
        greeting5.setTimestamp(Instant.EPOCH);

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
                .isNotEqualTo(greeting5);

        assertThat(greeting3)
                .isNotEqualTo(greeting1)
                .isNotEqualTo(greeting2)
                .isEqualTo(greeting4)
                .hasSameHashCodeAs(greeting4)
                .isNotEqualTo(greeting5);

        assertThat(greeting4)
                .isNotEqualTo(greeting1)
                .isNotEqualTo(greeting2)
                .isEqualTo(greeting3)
                .hasSameHashCodeAs(greeting3)
                .isNotEqualTo(greeting5);

        assertThat(greeting5)
                .isNotEqualTo(greeting1)
                .isNotEqualTo(greeting2)
                .isNotEqualTo(greeting3)
                .isNotEqualTo(greeting4);
    }
}
