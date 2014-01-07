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
 * @author Admin
 */
@Component(value = "registrationformvalidator")
public class RegistrationFormValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    
         UserRegistrationForm form = (UserRegistrationForm) target;
         
         if(!form.getPassword().equals(form.getrPassword()))
             errors.rejectValue("password", "password's don't same");
         if(!form.getEmail().equals(form.getrEmail()))
             errors.rejectValue("email", "emails's don't same");
    
    }
    
    
    
}
