package com.cartisan.common.specifications;

/**
 * @author colin
 */
public class OrSpecification<T> extends AbstractSpecification<T> {
    private final Specification<T> specification1;
    private final Specification<T> specification2;

    public OrSpecification(Specification<T> specification1, Specification<T> specification2) {
        this.specification1 = specification1;
        this.specification2 = specification2;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return specification1.isSatisfiedBy(t) || specification2.isSatisfiedBy(t);
    }
}
