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
@Constraint(validatedBy = StringListValue.Validator.class)
public @interface StringListValue {
    String message() default "{com.cartisan.validator.ListValue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] values() default{};

    class Validator implements ConstraintValidator<StringListValue, String> {
        private Set<String> defines = new HashSet<>();
        @Override
        public void initialize(StringListValue constraintAnnotation) {
            for (String value : constraintAnnotation.values()) {
                defines.add(value);
            }
        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return defines.contains(value);
        }
    }
}
