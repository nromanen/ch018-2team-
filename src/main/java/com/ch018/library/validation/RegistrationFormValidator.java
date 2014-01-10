package com.ch018.library.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
/**
 *
 * @author Edd Arazian
 */
@Component(value = "registrationformvalidator")
public class RegistrationFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationForm.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
         UserRegistrationForm form = (UserRegistrationForm) target;
         if (!form.getPassword().equals(form.getrPassword()))
             errors.rejectValue("password", "password's don't match");
         if (!form.getEmail().equals(form.getrEmail()))
             errors.rejectValue("email", "emails's don't match");
    }
}
