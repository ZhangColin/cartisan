package com.cartisan.dp;

import com.cartisan.domain.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author colin
 */
@Getter
@AllArgsConstructor
public class IdName<TId, TName> implements ValueObject<IdName<TId, TName>> {
    private TId id;
    private TName name;

    protected IdName() {

    }

    @Override
    public boolean sameValueAs(IdName<TId, TName> other) {
        return id.equals(other.id) && name.equals(other.name);
    }
}
