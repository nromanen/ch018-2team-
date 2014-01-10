package com.ch018.library.service;

import com.ch018.library.entity.Person;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 *
 * @author Admin
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonService personService;
    
    private final Logger logger = LoggerFactory.getLogger(MyUserDetailsService.class);
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personService.getByEmail(username);
        if (person == null) {
            logger.info("user {} not found", username);
            throw new UsernameNotFoundException(username + "Not Found");
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(person.getProle());   
        UserDetails user =  new User(person.getEmail(), person.getPassword(), true, true, true, true, authorities);
        logger.info("user {} registered", username);
        return user;  
    } 
}