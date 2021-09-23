package com.cartisan.domains;

import java.util.Objects;

/**
 * @author colin
 */
public class AbstractIdentity<T> implements Identity<T> {
    private T value;

    public AbstractIdentity(T value) {
        this.setId(value);
    }

    @Override
    public T value() {
        return null;
    }

    private void setId(T value) {
        if (value == null) {
            throw new IllegalArgumentException("The identity is required.");
        }

        this.validateValue(value);
        this.value = value;
    }

    protected void validateValue(T value) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractIdentity<?> that = (AbstractIdentity<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " [id=" + value + "]";
    }
}
