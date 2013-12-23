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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    @RequestMapping(method = RequestMethod.GET)
    public String order(@RequestParam("bookid") Integer bId, Model model){
        useService.save(new BooksInUse());
        System.out.println("======+++++++++++++++++++ BEFORE CREATE" + bId);
        Book book = bService.getBookById(bId);
        System.out.println("======+++++++++++++++++++ BEFORE CREATE");
        String minDate = createMinTime(book);
        System.out.println("======+++++++++++++++++++ AFTER CREATE");
        model.addAttribute("book", book);
        model.addAttribute("minDate", minDate);
        return "bookorder";   
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.GET)
        public String addOrder(@RequestParam("bookid") int bId, @RequestParam("date") long date, Model model) throws Exception {
                
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
    
    
    private String createMinTime(Book book){
        System.out.println("======+++++++++++++++++++ IN CREATRE");
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
