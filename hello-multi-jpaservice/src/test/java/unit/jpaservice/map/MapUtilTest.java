package unit.jpaservice.map;

import org.instancio.Instancio;
import org.joelpop.hellomulti.jpaservice.map.MapUtil;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class MapUtilTest {

    @Test
    void listMapperStrings() {
        var strings = Instancio.createList(String.class);

        var lowercaseStrings = MapUtil.map(strings, String::toLowerCase);
        assertThat(lowercaseStrings)
                .isEqualTo(strings.stream().map(String::toLowerCase).toList());

        var uppercaseStrings = MapUtil.map(strings, String::toUpperCase);
        assertThat(uppercaseStrings)
                .isEqualTo(strings.stream().map(String::toUpperCase).toList());
    }

    @Test
    void listMapperClassToRecordAndBack() {
        var personClasses = Instancio.createList(PersonClass.class);

        var personRecords = MapUtil.map(personClasses,
                personClass -> new PersonRecord(personClass.getFirstName(),
                        personClass.getLastName()));
        assertThat(personRecords)
                .containsExactly(personClasses.stream()
                        .map(personClass -> new PersonRecord(personClass.getFirstName(), personClass.getLastName()))
                        .toArray(PersonRecord[]::new));

        var personClasses2 = MapUtil.map(personRecords,
                personRecord -> new PersonClass(personRecord.firstName(),
                        personRecord.lastName()));
        assertThat(personClasses2)
                .containsExactly(personClasses.toArray(new PersonClass[0]));
    }

    private static class PersonClass {
        private final String firstName;
        private final String lastName;

        public PersonClass(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        @Override
        public final boolean equals(Object that) {
            if (that == this) {
                return true;
            }
            if (!(that instanceof PersonClass personClass)) {
                return false;
            }

            return Objects.equals(firstName, personClass.firstName) &&
                    Objects.equals(lastName, personClass.lastName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(firstName, lastName);
        }
    }

    private record PersonRecord(String firstName, String lastName) {}
}
