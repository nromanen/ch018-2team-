/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.entity.Person;
import java.util.Collection;
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
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    PersonService personService;
    
   
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    
        
        
        
        Person person = personService.getByEmail(username);
        if(person == null)
            throw new UsernameNotFoundException(username + "Not Found");
        
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
        
        UserDetails user =  new User(person.getEmail(), person.getPassword(), true, true, true, true, authorities);
        System.out.println("userName = " + user.getUsername());
        System.out.println("roles = " + user.getAuthorities());
        
        return user;
        
        
    }
    
   
    
    
    
}
