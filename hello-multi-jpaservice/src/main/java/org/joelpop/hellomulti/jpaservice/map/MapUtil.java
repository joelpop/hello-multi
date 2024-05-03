package org.joelpop.hellomulti.jpaservice.map;

import org.joelpop.hellomulti.shared.util.LibraryClass;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public final class MapUtil extends LibraryClass {

    private MapUtil() throws IllegalAccessException {
        // library class - not intended for instantiation
    }

    /**
     * Map a List of "T" objects to a List of "R" objects.
     * <p>
     *     Example usage:<br>
     *     {@code List<Item> mappedItems = MapUtil.map(items, ItemMap::map);}
     * </p>
     *
     * @param list the list of "T" objects to map
     * @param mapper the function to map a "T" object to a "R" object
     * @return the mapped list of "R" objects
     * @param <T> the type of the objects to map
     * @param <R> the type of the mapped objects
     */
    public static <T,R> List<R> map(List<T> list, Function<T,R> mapper) {
        return Optional.ofNullable(list).orElse(List.of()).stream()
                .map(mapper)
                .toList();
    }
}
