package com.ch018.library.service;

import com.ch018.library.entity.Person;
import com.ch018.library.validation.Password;
import com.ch018.library.validation.UserRegistrationForm;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
/**
 *
 * @author Edd Arazian
 */
@Service
public interface PersonService {
        void save(Person person);
        void delete(int id);
        void update(Person person);
        List<Person> getAll();
        Person getById(int id);
        Person getByEmail(String email);
        List<Person> getByName(String name);
        List<Person> getBySurname(String surname);
        Person getByCellPhone(String cellphone);
        List<Person> getByRole(String role);
        List<Person> getConfirmed();
        List<Person> getSmsEnabled();
        boolean changeEmail(String email, Person person);
        boolean updatePassword(Password password, Principal principal);
        String updateField(String fieldName, String filedValue);
        List<Person> simpleSearch(String request);
        List<Person> advancedSearch(Person person);
        Person countRating(Person person); 
        boolean register(UserRegistrationForm form);
        boolean confirmMail(String key);
        boolean restoreSendEmail(String email);
        boolean isKeyValid(String key);
        boolean restorePass(String key, Password password);
}
