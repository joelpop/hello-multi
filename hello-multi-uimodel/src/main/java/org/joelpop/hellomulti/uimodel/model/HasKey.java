package org.joelpop.hellomulti.uimodel.model;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.ToIntFunction;

/**
 * For objects whose equality and hashCode depend primarily on their key.
 * <p>
 * Only if they have no key are their members used for calculating equals and hashCode.
 * <p>
 * This is useful in a scenario, such as in the item list of a combo box,
 * when the objects are sparse, so
 * not all members are needed or available for calculating equals and hashCode.
 */
public interface HasKey<V> {

    V getKey();

    void setKey(V key);

    /**
     * Compare two {@code HasKey} objects for equality.
     * <p>
     * If either object's key is non-null,
     * their keys are compared. If both of their keys are null, member equality is tested.
     *
     * @param aHasKey one of the two objects to compare for equality
     * @param bHasKey the other of the two objects to compare for equality
     * @param membersEqual the predicate to use for comparing members for equality
     * @return {@code true}, if objects are equal; {@code false}, otherwise
     * @param <A> type of the first object
     * @param <B> type of the other object
     * @param <V> type of the key
     */
    static <A extends HasKey<V>, B extends HasKey<V>, V>
    boolean equals(A aHasKey, B bHasKey, BiPredicate<A, B> membersEqual) {
        var aKey = aHasKey.getKey();
        var bKey = bHasKey.getKey();

        if (aKey == null && bKey == null) {
            return membersEqual.test(aHasKey, bHasKey);
        }

        return Objects.equals(aKey, bKey);
    }

    /**
     * Calculate the hashCode of a {@code HasKey} object.
     * <p>
     * If the object's key is non-null, it's hashCode used.
     * Otherwise, the hashCode of the members is used.
     *
     * @param hasKey the object to calculate the hashCode of
     * @param memberHashCode the function to calculate the member hashCode from
     * @return the hashCode of the object
     * @param <T> the type of the object
     * @param <V> type of the key
     */
    static <T extends HasKey<V>, V>
    int hashCode(T hasKey, ToIntFunction<T> memberHashCode) {
        return Optional.ofNullable(hasKey.getKey())
                .map(Objects::hashCode)
                .orElse(memberHashCode.applyAsInt(hasKey));
    }
}
