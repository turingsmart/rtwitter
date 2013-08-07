package com.springapp.mvc.service;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created with IntelliJ IDEA.
 * User: maverick
 * Date: 6/8/13
 * Time: 7:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class usernameExistsConstraintValidator implements ConstraintValidator<usernameExistsConstraint, String> {

    @Autowired
    private DbRepositoryService dbRepositoryService;


    @Override
    public void initialize(usernameExistsConstraint constraint) {

    }

    @Override
    public boolean isValid(String target, ConstraintValidatorContext context) {
        if(dbRepositoryService.fetchUser(target) == null)
            return true;
        return false;
    }
}