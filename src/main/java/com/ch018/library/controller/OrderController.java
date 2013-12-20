/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Admin
 */
@Controller
public class OrderController {
    
    @Autowired
    BookService bService;
    @Autowired
    PersonService pService;
    @Autowired
    OrdersService oService;
    
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String order(@RequestParam("bookid") Integer bId, @RequestParam("personid") Integer pId, Model model){
        Book book = bService.getBookById(bId);
        Person person = pService.getById(pId);
        List<Orders> bis = oService.getOrderByBook(book);
        model.addAttribute("book", book);
        model.addAttribute("person", person);
        model.addAttribute("biulist", bis);
        return "bookorder";   
    }
    
    @RequestMapping(value = "/addOrder", method = RequestMethod.GET)
        public String addOrder(@RequestParam("id") int id, Model model) throws Exception {

                model.addAttribute("person", pService.getById(id));
                model.addAttribute("book", bService.getAll());
                
                return "addOrder";
        }

        @RequestMapping(value = "/addOrder", method = RequestMethod.POST)
        public String addOrder(@RequestParam("personId") int personId, @RequestParam("bookId") int bookId, Model model) throws Exception {
                Date date = new Date();
                Orders orders = new Orders(pService.getById(personId), bService.getBookById(bookId), date);
                oService.save(orders);
                
                
                return "redirect:/addOrder";
        }
    
}
