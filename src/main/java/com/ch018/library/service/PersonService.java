package com.ch018.library.service;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.validation.Password;
import com.ch018.library.validation.PersonEditValidator;
import com.ch018.library.validation.PersonalInfo;
import com.ch018.library.validation.UserRegistrationForm;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
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
        void changeEmail(String email, Person person, String path) throws Exception;
        void updatePassword(Password password, Person person) throws Exception;
        List<Person> simpleSearch(String request);
        List<Person> advancedSearch(Person person);
        Person countRating(Person person); 
        boolean register(UserRegistrationForm form, String path);
        boolean confirmMail(String key, HttpServletRequest request);
        boolean restoreSendEmail(String email, String path);
        boolean isKeyValid(String key);
        boolean restorePass(String key, Password password);
        void updatePersonalInfo(Person person, PersonalInfo info) throws Exception;
        void update(PersonEditValidator person);
        List<BooksInUse> getUsingBooks(Person person);
        List<Person> orderByName();
        List<Person> orderBySurname();
        List<Person> orderByRating();
        List<Person> pagination(int pageNumber);
}
