package com.cartisan.repositories;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

                        switch (query.type()) {
                            case INNER_LIKE:
                                return criteriaBuilder.like(root.get(attributeName), "%" + val.toString() + "%");
                        }
                    }

                    field.setAccessible(accessible);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        };
    }

    private static List<Field> getAllFields(Class clazz) {
        if (Objects.isNull(clazz)) {
            return Collections.emptyList();
        }

        final List<Field> fields = asList(clazz.getDeclaredFields());
        fields.addAll(getAllFields(clazz.getSuperclass()));

        return fields;
    }
}
