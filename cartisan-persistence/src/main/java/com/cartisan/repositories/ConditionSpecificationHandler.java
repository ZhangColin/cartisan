package com.cartisan.repositories;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author colin
 */
@FunctionalInterface
public interface ConditionSpecificationHandler {
    /**
     *
     * @param root
     * @param criteriaBuilder
     * @param fieldType
     * @param attributeName
     * @param val
     * @return
     */
    Predicate toPredicate(Root<?> root, CriteriaBuilder criteriaBuilder,
                              Class<?> fieldType, String attributeName, Object val);
}
