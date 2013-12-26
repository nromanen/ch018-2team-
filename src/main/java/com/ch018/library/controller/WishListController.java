/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping(value = "/books/wishlist")
public class WishListController {
    
    @Autowired
    WishListService wService;
    @Autowired
    PersonService pService;
    @Autowired
    BookService bService;
    @Autowired
    BookInUseService useService;
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(@RequestParam("bookid") int bookId){
        Person person = pService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Book book = bService.getBookById(bookId);
        WishList wish = new WishList(person, book);
        wService.save(wish);
        return "redirect:/books";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("bookid") int bookId){
        Person person = pService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        Book book = bService.getBookById(bookId);
        WishList wish = wService.getWishByPersonBook(person, book);
        wService.delete(wish);
        return "redirect:/books/wishlist/my";
    }
    
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String myList(Model model){
        Person person = pService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        List<WishList> wishes  = new ArrayList<>();
        try{
            wishes = wService.getWishByPerson(person);
        }catch(Exception e){
            System.out.println(e);
        }
        System.out.println(wishes);
        Map<Book, String> booksWithDate = new HashMap<>();
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH");
        Date free;
        for(WishList wish : wishes){
            Book book = wish.getBook();
            free = useService.getBookWithLastDate(book);
            if(book.getCurrentQuantity() > 0 || free == null)
                free = new Date();
                
            booksWithDate.put(book, format.format(free).toString());
        }
        System.out.println(wishes); 
        model.addAttribute("map", booksWithDate);
        return "wishlist";
    }
    
    
    
}
