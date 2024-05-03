package org.joelpop.hellomulti.shared.util;

public abstract class LibraryClass {

    protected LibraryClass() throws IllegalAccessException {
        throw new IllegalAccessException("%s is a library class - it is not intended to be instantiated"
                .formatted(getClass().getSimpleName()));
    }

}
