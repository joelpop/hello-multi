package org.joelpop.hellomulti.uimodel.model;

import java.time.Instant;
import java.util.Objects;

public final class Greeting implements HasLongKey {
    private Long key;
    private String name;
    private String message;
    private Instant timestamp;

    public Greeting() {
        // no arg constructor
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Object is considered equal if it
     * <ul>
     *     <li>is the same object, or</li>
     *     <li>is an instance of the same type and all member values are equal</li>
     * </ul>
     * @param that an object to compare with this one
     * @return {@code true}, if the objects are considered equal; {@code false} otherwise
     */
    @Override
    public boolean equals(Object that) {
        if (that == this) {
            return true;
        }
        if (!(that instanceof Greeting greeting)) {
            return false;
        }
        return HasKey.equals(this, greeting, (a, b) ->
                Objects.equals(a.name, b.name) &&
                        Objects.equals(a.message, b.message) &&
                        Objects.equals(a.timestamp, b.timestamp));
    }

    @Override
    public int hashCode() {
        return HasKey.hashCode(this, t -> Objects.hash(t.name, t.message, t.timestamp));
    }

    @Override
    public String toString() {
        return "Greeting[" +
                "key:" + key + "," +
                "name:" + name + "," +
                "message:" + message + "," +
                "timestamp:" + timestamp + ']';
    }
}
