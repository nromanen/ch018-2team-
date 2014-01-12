/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Edd Arazian
 */
@Component("passwordvalidator")
public class PasswordValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Password.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Password password = (Password) target;
        if (!password.getNewPass().equals(password.getrNewPass()))
            errors.rejectValue("rNewPass", "password's don't match", "password's don't match");
    }
    
    
}
