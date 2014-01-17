/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;

/**
 *
 * @author Edd Arazian
 */
@Controller
public class LoginController {

        @Autowired
        private PersonService pService;
        @Autowired
        private BookService bService;

        @RequestMapping(value = "/")
        public String loginProcess(HttpServletRequest req, Authentication auth) {
            if (auth != null && auth.isAuthenticated()) {
                if (req.isUserInRole("ROLE_USER")) {
                    return "redirect:/books/";
                } else if (req.isUserInRole("ROLE_LIBRARIAN")) {
                    return "redirect:/librarian/books";
                } else if (req.isUserInRole("ROLE_ADMIN")) {
                    return "redirect:/admin";
                }
            }
            return "redirect:/books";
        }


        @RequestMapping(value = "/login", method = RequestMethod.GET)
        public String loginProcess() {
            return "redirect:/";
        }

        @RequestMapping(value = "/denied")
        public String denied() {
            return "denied";
        }

        @RequestMapping(value = "/error")
        public String error() {
            return "error";
        }
        
        @RequestMapping(value = "/errors/404")
        public String error404() {
            return "error404";
        }
}
