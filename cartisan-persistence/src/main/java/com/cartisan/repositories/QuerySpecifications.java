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
                        if (!StringUtils.isEmpty(blurry)) {
                            final Predicate[] orPredicates = Arrays.stream(blurry.split(","))
                                    .map(b -> criteriaBuilder.like(root.get(b).as(String.class), "%" + val.toString() + "%"))
                                    .toArray(Predicate[]::new);
                            predicates.add(criteriaBuilder.or(orPredicates));
                            continue;
                        }
                        predicates.add(handlerOf(query.type()).toPredicate(root, criteriaBuilder, fieldType, attributeName, val));
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

    private static QuerySpecificationHandler handlerOf(Query.Type queryType) {
        return handlers.get(queryType);
    }

    private static Map<Query.Type, QuerySpecificationHandler> handlers =
            new HashMap<Query.Type, QuerySpecificationHandler>() {{
                put(Query.Type.EQUAL, (root, criteriaBuilder, fieldType, attributeName, val) -> criteriaBuilder.equal(root.get(attributeName).as(fieldType), val));

                put(Query.Type.NOT_EQUAL, (root, criteriaBuilder, fieldType, attributeName, val) ->
                        criteriaBuilder.notEqual(root.get(attributeName).as(fieldType), val));

                put(Query.Type.GREATER_EQUAL, (root, criteriaBuilder, fieldType, attributeName, val) ->
                        criteriaBuilder.greaterThanOrEqualTo(root.get(attributeName).as((Class<? extends Comparable>) fieldType), (Comparable) val));

                put(Query.Type.GREATER, (root, criteriaBuilder, fieldType, attributeName, val) ->
                        criteriaBuilder.greaterThan(root.get(attributeName).as((Class<? extends Comparable>) fieldType), (Comparable) val));

                put(Query.Type.LESS_EQUAL, (root, criteriaBuilder, fieldType, attributeName, val) ->
                        criteriaBuilder.lessThanOrEqualTo(root.get(attributeName).as((Class<? extends Comparable>) fieldType), (Comparable) val));

                put(Query.Type.LESS, (root, criteriaBuilder, fieldType, attributeName, val) ->
                        criteriaBuilder.lessThan(root.get(attributeName).as((Class<? extends Comparable>) fieldType), (Comparable) val));

                put(Query.Type.INNER_LIKE, (root, criteriaBuilder, fieldType, attributeName, val) ->
                        criteriaBuilder.like(root.get(attributeName).as(String.class), "%" + val.toString() + "%"));

                put(Query.Type.LEFT_LIKE, (root, criteriaBuilder, fieldType, attributeName, val) ->
                        criteriaBuilder.like(root.get(attributeName).as(String.class), "%" + val.toString()));

                put(Query.Type.RIGHT_LIKE, (root, criteriaBuilder, fieldType, attributeName, val) ->
                        criteriaBuilder.like(root.get(attributeName).as(String.class), val.toString() + "%"));

                put(Query.Type.IN, (root, criteriaBuilder, fieldType, attributeName, val) -> {
                    final Collection<Object> ins = (Collection<Object>) val;
                    if (!ins.isEmpty()) {
                        return root.get(attributeName).in(ins);
                    }
                    return null;
                });

                put(Query.Type.BETWEEN, (root, criteriaBuilder, fieldType, attributeName, val) -> {
                    final List<Object> between = (List<Object>) val;
                    return criteriaBuilder.between(root.get(attributeName).as((Class<? extends Comparable>) between.get(0).getClass()), (Comparable) between.get(0), (Comparable) between.get(1));
                });
            }};

    private static List<Field> getAllFields(Class<?> clazz) {
        if (Objects.isNull(clazz)) {
            return Collections.emptyList();
        }

        final List<Field> fields = asList(clazz.getDeclaredFields());
        fields.addAll(getAllFields(clazz.getSuperclass()));

        return fields;
    }
}
