package com.cartisan.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@FunctionalInterface
public interface QuerySpecificationHandler {
    Predicate toPredicate(Root<?> root, CriteriaBuilder criteriaBuilder,
                              Class<?> fieldType, String attributeName, Object val);
}
