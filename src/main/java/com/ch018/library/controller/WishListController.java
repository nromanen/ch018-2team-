/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;

import com.ch018.library.DAO.PersonDao;
import com.ch018.library.controller.errors.IncorrectDate;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.entity.WishList;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.WishListService;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.annotate.JsonAnyGetter;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping(value = "/books/wishlist")
public class WishListController {
    
    @Autowired
    WishListService wishService;
    @Autowired
    PersonService personService;
    @Autowired
    BookService bookService;
    @Autowired
    BookInUseService useService;
    @Autowired
    OrdersService ordersService;
   
    final Logger logger = LoggerFactory.getLogger(WishListController.class);
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody String add(@RequestParam("bookId") Integer bookId, 
            Principal principal){
        Person person = personService.getByEmail(principal.getName());
        Book book = bookService.getBookById(bookId);
        WishList wish = new WishList(person, book);
        wishService.save(wish);
        JSONObject json = new JSONObject();
        json.put("title", book.getTitle());
        return json.toString();
    }
    
    @RequestMapping(value="/my", method = RequestMethod.GET)
    public String myG(Model model, Principal principal){
        Person person = personService.getByEmail(principal.getName());
        List<WishList> wishes = wishService.getWishByPerson(person);
        Map<WishList, Long> wishesWithDates = new HashMap<>();
        for(WishList wish : wishes){
            wishesWithDates.put(wish, useService.getMinOrderDate(wish.getBook()).getTime());
        }
        model.addAttribute("map", wishesWithDates);
        return "user/wishlist";
    }

    
    @RequestMapping(value = "/delete")
    public @ResponseBody String delete(@RequestParam("wishId") Integer wishId){
        wishService.delete(wishService.getWishByID(wishId));
        return new JSONObject().toString();
    }
    
   /* @RequestMapping(value = "/confirm")
    public @ResponseBody String add(@RequestParam("wishId") Integer wishId, @RequestParam("bookId") Integer bookId,
        @RequestParam("date") String date, Principal principal) throws IncorrectDate{
        Person person = personService.getByEmail(principal.getName());
        Book book = bookService.getBookById(bookId);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        Date d;
        
        try{
            d = format.parse(date);
            
        }catch(Exception e){
           
            throw new IncorrectDate("incorrect date");
        }
        ordersService.save(new Orders(person, book, d));
        wishService.delete(wishService.getWishByID(wishId));
        return new JSONObject().toString();
                
        
    }*/
    
    
   /* private String calculateMinDate(Book book){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date minDate;
        int current = book.getCurrentQuantity();
       Date dateFromUse = useService.getBookWithLastDate(book);
        if(dateFromUse == null || current > 0)
            minDate = new Date();
        else{
            minDate = dateFromUse;
        }
        return "";
             
    }*/
    
}
