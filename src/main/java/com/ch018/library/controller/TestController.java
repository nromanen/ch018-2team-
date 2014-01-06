/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.MailService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Admin6
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {
    
    @Autowired
    BookService bookService;
    @Autowired
    GenreService genreService;
    @Autowired
    PersonService personService;
    @Autowired
    BookInUseService useService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    MailService mailService;
    
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String test(Model model){
        
        mailService.sendMail("springytest@gmail.com", "etenzor@gmail.com", "SpringyTest", "For test purpose only");
        
        return "test";
    }
    
    @RequestMapping(value = "/tiletest", method = RequestMethod.GET)
    public ModelAndView tiles(){
        return new ModelAndView("books");
    }
}
