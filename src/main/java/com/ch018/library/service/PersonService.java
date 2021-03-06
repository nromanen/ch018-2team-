package com.ch018.library.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.exceptions.EmailAlreadyInUseException;
import com.ch018.library.exceptions.EmailNotChangedException;
import com.ch018.library.exceptions.OldPasswordIncorrectException;
import com.ch018.library.validation.Password;
import com.ch018.library.validation.PersonEditValidator;
import com.ch018.library.validation.PersonalInfo;
import com.ch018.library.validation.UserRegistrationForm;
/**
 *
 * @author Edd Arazian
 */
@Service
public interface PersonService {
        void save(Person person);
        boolean delete(int id);
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
        void changeEmail(String email, Person person, String path) throws EmailAlreadyInUseException, EmailNotChangedException;
        void updatePassword(Password password, Person person) throws OldPasswordIncorrectException;
        List<Person> simpleSearch(String request);
        List<Person> advancedSearch(Person person);
        Person countRating(Person person); 
        Person register(UserRegistrationForm form, String path) throws HibernateException;
        boolean confirmMail(String key);
        boolean restoreSendEmail(String email, String path);
        boolean isKeyValid(String key);
        boolean restorePass(String key, Password password);
        void updatePersonalInfo(Person person, PersonalInfo info) throws Exception;
        void update(PersonEditValidator person);
        List<BooksInUse> getUsingBooks(Person person);
        List<Person> orderByName();
        List<Person> orderBySurname();
        List<Person> orderByRating();
}
