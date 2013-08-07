package com.springapp.mvc.service;

/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 6/8/13
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy=usernameExistsConstraintValidator.class)

public @interface usernameExistsConstraint {
    String message() default "Username exists";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
