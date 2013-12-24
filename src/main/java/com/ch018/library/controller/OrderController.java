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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author Admin
 */
@Controller
@RequestMapping(value = "/books/order")
public class OrderController {
    
    @Autowired
    BookService bService;
    @Autowired
    PersonService pService;
    @Autowired
    OrdersService oService;
    @Autowired
    BookInUseService useService;
    @Autowired
    SessionFactory fact;
    
    @RequestMapping(method = RequestMethod.GET)
    public String order(@RequestParam("bookid") Integer bId, Model model){
        useService.save(new BooksInUse());
        Book book = bService.getBookById(bId);
        String minDate = createMinTime(book);
        model.addAttribute("book", book);
        model.addAttribute("minDate", minDate);
        return "bookorder";   
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
        public String addOrder(@RequestParam("bookid") int bId, @RequestParam("date") long date, Model model) {
                
                Book book = bService.getBookById(bId);
                Person person = pService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
                
                    
                Orders order = new Orders(person, book, new Date(date));
                try{
                    oService.save(order);
                }catch(Exception e){
                    return "unsuccessful";
                }
                return "redirect:/books";
        }
    
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public String edit(@RequestParam("bookid") int bId, @RequestParam("date") long date){
            Person person = pService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            //Orders order = oService.getOrderIdByPersonIdBookId(person.getPid(), bId);
            Orders order = new Orders(person, bService.getBookById(bId), new Date(date));
            Orders tmp = oService.getOrderIdByPersonIdBookId(person.getPid(), bId);
            try{
            oService.update(tmp.getId() , order);
            
            
            }catch(Exception e){
                System.out.println(e);
            }
           
            return "books";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam("id") int id){
        Orders order = oService.getOrderByID(id);
        try{
        oService.delete(order);
        }catch(Exception e){
            System.out.println(e);
        }
        return "redirect:/books/order/my";
    }
    
    @RequestMapping(value = "/my", method = RequestMethod.GET)
    public String myOrders(Model model){
        List<Orders> orders = oService.getOrderByPerson(pService.getByEmail(SecurityContextHolder.getContext()
                .getAuthentication().getName()));
        
        Map<Orders, Date> map = new HashMap<>();
        for(Orders order : orders) {
            Date minDate;
            try{
                minDate = useService.getBookWithLastDate(order.getBook());
                System.out.println("MINDATE " + minDate);
                map.put(order, minDate);
            }catch(Exception e){
                System.out.println(e);
                map.put(order, new Date());
            }
        }
        model.addAttribute("map", map);
        return "orders";
        
    }

    
    private String createMinTime(Book book){
        Long mindate;
        try {
            mindate = useService.getBookWithLastDate(book).getTime();
        } catch (Exception e) {
            System.out.println("CATCH EXC");
            mindate = new Date().getTime();
        }
        System.out.println(mindate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(mindate));
        StringBuilder sb = new StringBuilder();
        sb.append(calendar.get(Calendar.YEAR));
        sb.append("/");
        sb.append(calendar.get(Calendar.MONTH) + 1);
        sb.append("/");
        sb.append(calendar.get(Calendar.DAY_OF_MONTH));
        sb.append(" ");
        sb.append(calendar.get(Calendar.HOUR));
        return sb.toString();
    }
}
