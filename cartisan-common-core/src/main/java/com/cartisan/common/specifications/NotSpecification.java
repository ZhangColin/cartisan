package com.cartisan.common.specifications;

/**
 * @author colin
 */
public class NotSpecification<T> extends AbstractSpecification<T> {
    private final Specification<T> specification;

    public NotSpecification(Specification<T> specification) {
        this.specification = specification;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return !specification.isSatisfiedBy(t);
    }
}
