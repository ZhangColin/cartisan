package com.cartisan.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.*;

import static java.util.Arrays.asList;

/**
 * @author colin
 */
@Slf4j
public final class QuerySpecifications {
    private QuerySpecifications() {
    }

    public static <T, S> Specification<T> querySpecification(S searchParam) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if (Objects.isNull(searchParam)) {
                return null;
            }

            final List<Predicate> predicates = new ArrayList<>();
            final List<Field> allFields = getAllFields(searchParam.getClass());
            try {
                for (Field field : allFields) {
                    boolean accessible = field.isAccessible();
                    field.setAccessible(true);

                    final Query query = field.getAnnotation(Query.class);
                    if (!Objects.isNull(query)) {
                        String propName = query.propName();
                        String attributeName = StringUtils.isEmpty(propName) ? field.getName() : propName;
                        final Class<?> fieldType = field.getType();

                        final Object val = field.get(searchParam);

                        if (Objects.isNull(val) || "".equals(val)) {
                            continue;
                        }

                        String blurry = query.blurry();
                        if (StringUtils.isEmpty(blurry)) {
                            final Predicate[] orPredicates = Arrays.stream(blurry.split(","))
                                    .map(b -> criteriaBuilder.like(root.get(b).as(String.class), "%" + val.toString() + "%"))
                                    .toArray(Predicate[]::new);
                            predicates.add(criteriaBuilder.or(orPredicates));
                            continue;
                        }

                        switch (query.type()) {
                            case EQUAL:
                                predicates.add(criteriaBuilder.equal(root.get(attributeName).as(fieldType), val));
                                break;
                            case NOT_EQUAL:
                                predicates.add(criteriaBuilder.notEqual(root.get(attributeName).as(fieldType), val));
                                break;
                            case GREATER_EQUAL:
                                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(attributeName).as((Class<? extends Comparable>) fieldType), (Comparable) val));
                                break;
                            case GREATER:
                                predicates.add(criteriaBuilder.greaterThan(root.get(attributeName).as((Class<? extends Comparable>) fieldType), (Comparable) val));
                                break;
                            case LESS_EQUAL:
                                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(attributeName).as((Class<? extends Comparable>) fieldType), (Comparable) val));
                                break;
                            case LESS:
                                predicates.add(criteriaBuilder.lessThan(root.get(attributeName).as((Class<? extends Comparable>) fieldType), (Comparable) val));
                                break;
                            case INNER_LIKE:
                                predicates.add(criteriaBuilder.like(root.get(attributeName).as(String.class), "%" + val.toString() + "%"));
                                break;
                            case LEFT_LIKE:
                                predicates.add(criteriaBuilder.like(root.get(attributeName).as(String.class), "%" + val.toString()));
                                break;
                            case RIGHT_LIKE:
                                predicates.add(criteriaBuilder.like(root.get(attributeName).as(String.class), val.toString() + "%"));
                                break;
                            case IN:
                                final Collection<Object> ins = (Collection<Object>) val;
                                if (!ins.isEmpty()) {
                                    predicates.add(root.get(attributeName).in(ins));
                                }
                                break;
                            case BETWEEN:
                                final List<Object> between = (List<Object>) val;
                                predicates.add(criteriaBuilder.between(root.get(attributeName).as((Class<? extends Comparable>) between.get(0).getClass()), (Comparable) between.get(0), (Comparable) between.get(1)));
                                break;
                            default:
                                break;
                        }
                    }

                    field.setAccessible(accessible);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (predicates.isEmpty()) {
                return null;
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static List<Field> getAllFields(Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            return Collections.emptyList();
        }

        final List<Field> fields = asList(clazz.getDeclaredFields());
        fields.addAll(getAllFields(clazz.getSuperclass()));

        return fields;
    }
}
