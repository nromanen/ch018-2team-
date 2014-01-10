/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Edd Arazian
 */
@Controller
//@RequestMapping(value = "/use")
public class BookInUseController {
    
    @Autowired
    BookService bService;
    @Autowired
    PersonService pService;
    @Autowired
    BookInUseService useService;
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute("persons", pService.getAll());
        model.addAttribute("books", bService.getAll());
        return "addBookToUser";
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam("pId") Integer pId, @RequestParam("bId") Integer bId){
        Person person = pService.getById(pId);
        Book book = bService.getBookById(bId);
        if(book.getCurrentQuantity() <= 0)
            return "unsuccessful";
        BooksInUse bis = new BooksInUse();
        bis.setPerson(person);
        bis.setBook(book);
        bis.setIssueDate(new Date());
        bis.setReturnDate(new Date(System.currentTimeMillis() + (14*24*60*60*1000)));
        useService.save(bis);
        book.setCurrentQuantity(book.getCurrentQuantity() - 1);
        bService.update(book);
        return "redirect:/add";
        
    }
}
