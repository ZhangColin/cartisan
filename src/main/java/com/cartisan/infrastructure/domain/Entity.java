package com.cartisan.infrastructure.domain;

public interface Entity<TEntity, TId> {
    boolean sameIdentityAs(TEntity other);

    TId getId();

    default void setId(TId tId) {

    }

    default boolean isNew(){
        return getId() == null;
    }
}
