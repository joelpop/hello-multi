package org.joelpop.hellomulti.shared.util;

/**
 * Utility methods for functional interfaces.
 */
public final class FunctionalInterfaceUtil extends LibraryClass {

    private FunctionalInterfaceUtil() throws IllegalAccessException {
        // library class - not intended for instantiation
    }

    /**
     * No operation for {@link java.util.function.Consumer}.
     * Use when a Consumer is required, but no operation is needing to be done.
     *
     * @param ignored object to be ignored
     * @param <T> type of object being ignored
     */
    public static <T> void noOp(@SuppressWarnings("java:S1172") T ignored) {
        // do nothing
    }
}
