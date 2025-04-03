package unit.util;

import org.assertj.core.api.Condition;
import org.joelpop.hellomulti.shared.util.LibraryClass;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatObject;

public final class AssertUtil extends LibraryClass {

    private AssertUtil() throws IllegalAccessException {
        // library class - not intended for instantiation
    }

    /**
     * Asserts that setter of object correctly sets desired values
     * without having any internal side effects.
     * <p>
     * Example use for an object having a member {@code name}:
     * <pre>
     *     assertSetterHasNoSideEffects("name",
     *                 Greeting::new,
     *                 Greeting::getName,
     *                 Greeting::setName,
     *                 null, "", "World", null);
     * </pre>
     *
     * @param memberName name of object's member being tested
     * @param factory method reference to supplier of object
     * @param getter method reference to getter of object's member
     * @param setter method reference to setter of object's member
     * @param values array of values to test object's setter with
     * @param <T> type of object being tested
     * @param <V> type of member being tested
     */
    @SafeVarargs
    public static <T, V> void assertSetterHasNoSideEffects(String memberName,
                                                           Supplier<T> factory,
                                                           Function<T, V> getter,
                                                           BiConsumer<T, V> setter,
                                                           V... values) {
        T t = factory.get();

        Arrays.stream(values)
                .forEach(value -> {
                    setter.accept(t, value);
                    assertThat(getter.apply(t))
                            .isEqualTo(value);
                    assertThatObject(t)
                            .hasAllNullFieldsOrPropertiesExcept(memberName);
                });
    }

    public static <T, R> Condition<T> propertyPredicate(Function<T, R> getter, String propertyName,
                                                        Predicate<R> predicate) {
        return new Condition<>(t -> predicate.test(getter.apply(t)), propertyName);
    }

    public static <T, R> Condition<T> propertyIsNull(Function<T, R> getter, String propertyName) {
        return propertyPredicate(getter, propertyName, Objects::isNull);
    }

    public static <T, R, E> Condition<T> propertyBiPredicate(Function<T, R> getter, E expectedValue, String propertyName,
                                                             BiPredicate<R, E> biPredicate) {
        return new Condition<>(t -> biPredicate.test(getter.apply(t), expectedValue), propertyName);
    }

    public static <T, R> Condition<T> propertyIsEqualTo(Function<T, R> getter, R expectedValue, String propertyName) {
        return propertyBiPredicate(getter, expectedValue, propertyName, Objects::equals);
    }
}
