package com.ch018.library.controller;

import com.ch018.library.controller.errors.IncorrectDate;
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


import com.ch018.library.entity.Orders;
import com.ch018.library.service.OrdersService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(value = "/books/order")
public class OrderController {
    
    @Autowired
    BookService bookService;
    @Autowired
    PersonService personService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    BookInUseService useService;

    
    @RequestMapping(method = RequestMethod.POST)
    public @ResponseBody String order(@RequestParam("bookid") Integer bId){
        Book book = bookService.getBookById(bId);
        Date minReturn;
        
        if(book.getCurrentQuantity() > 0)
            minReturn = new Date();
        else{
            minReturn = useService.getBookWithLastDate(book);
            minReturn =  minReturn == null ? new Date() : minReturn;
        }
    
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        JSONObject json = new JSONObject();
        json.put("bId", book.getbId());
        json.put("img", book.getImg());
        json.put("title", book.getTitle());
        json.put("authors", book.getAuthors());
        json.put("publisher", book.getPublisher());
        json.put("description", book.getDescription());
        json.put("minReturn", format.format(minReturn).toString());
        return json.toString();   
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody String add(@RequestParam("bookId") Integer bookId, @RequestParam("time") String time, 
                        Principal principal) throws IncorrectDate{
        Book book = bookService.getBookById(bookId);
        Person person = personService.getByEmail(principal.getName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        Date date;
        try{
                date = format.parse(time);
        }catch(Exception e){
            throw new IncorrectDate("Incorrect Date");
        }
        ordersService.save(new Orders(person, book, date));
        
        return new JSONObject().toString();
    }
    
    /*
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
    }*/
}
