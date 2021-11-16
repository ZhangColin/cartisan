package com.cartisan.validator;


import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashSet;
import java.util.Set;

import static java.lang.annotation.ElementType.*;

/**
 * @author colin
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = IntegerListValue.Validator.class)
public @interface IntegerListValue {
    String message() default "{com.cartisan.validator.ListValue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] values() default{};

    class Validator implements ConstraintValidator<IntegerListValue, Integer> {
        private Set<Integer> defines = new HashSet<>();
        @Override
        public void initialize(IntegerListValue constraintAnnotation) {
            for (int value : constraintAnnotation.values()) {
                defines.add(value);
            }
        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            return defines.contains(value);
        }
    }
}
