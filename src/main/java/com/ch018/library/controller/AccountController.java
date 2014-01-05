/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.controller.errors.IncorrectDate;
import com.ch018.library.entity.Person;
import com.ch018.library.service.PersonService;
import java.security.Principal;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Edd Arazian
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {
    
    @Autowired
    PersonService personService;

    
    @RequestMapping(method = RequestMethod.GET)
    public String accountG(Model model, Principal principal){
        Person person = personService.getByEmail(principal.getName());
        model.addAttribute("person", person);
        return "user/account";
    }
    
    @RequestMapping(value = "changeEmail", method = RequestMethod.POST)
    public @ResponseBody String changeEmail(@RequestParam("email") String email, Principal principal){
        Person person = personService.getByEmail(principal.getName());
        person.setEmail(email);
        personService.update(person);
        person = personService.getByEmail(email);
        Authentication auth = new PreAuthenticatedAuthenticationToken(email, principal);
        
        Authentication auth1 = new RememberMeAuthenticationToken("secret", auth, SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        JSONObject json = new JSONObject();
        json.put("email", person.getEmail());
        return json.toString();
    }
    
    @RequestMapping(value = "changePassword", method = RequestMethod.POST)
    public @ResponseBody String changePassword(@RequestParam("oldPass") String oldPass,
                                                    @RequestParam("newPass") String newPass,
                                                    @RequestParam("reNewPass") String reNewPass,
                                                    Principal principal, HttpServletResponse response) throws Exception{
        if(personService.updatePassword(oldPass, newPass, reNewPass, principal))
            return new JSONObject().toString();
        else{
            throw new IncorrectDate("error occured during pass");
        }
    }
    
    @RequestMapping(value = "changeField", method = RequestMethod.POST)
    public @ResponseBody String changeField(@RequestParam("fieldName") String fieldName, 
                                              @RequestParam("fieldValue") String fieldValue) throws Exception {
        String field;
        if((field = personService.updateField(fieldName, fieldValue)) != null){
            JSONObject json = new JSONObject();
            json.put("field", field);
            return json.toString();
        }
            
        else
            throw new IncorrectDate("Incorrect " + fieldName);
    }
}