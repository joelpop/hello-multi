package org.joelpop.hellomulti.jpaclient.entity;

/**
 * Base entity with a primary key.
 *
 * @param <T> type of primary key
 */
public abstract class AbstractKeyEntity<T> {

    abstract T getKey();
}
