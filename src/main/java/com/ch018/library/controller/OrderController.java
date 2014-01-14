package com.ch018.library.controller;

import com.ch018.library.DAO.BookDao;
import com.ch018.library.controller.errors.IncorrectInput;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.PersonService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ch018.library.entity.Orders;
import com.ch018.library.helper.OrderDays;
import com.ch018.library.service.MailService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.WishListService;
import java.security.Principal;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author Edd Arazian
 */

@Controller
@RequestMapping(value = "/books/order")

public class OrderController {

        @Autowired
        private BookService bookService;
        @Autowired
        private PersonService personService;
        @Autowired
        private OrdersService ordersService;
        @Autowired
        private WishListService wishService;
        @Autowired
        private BookInUseService useService;
        @Autowired
        private MailService mailService;
        
        @Autowired
        private BookDao bookdao;

        final Logger logger = LoggerFactory.getLogger(OrderController.class);

        @RequestMapping(method = RequestMethod.GET)
        public String orderGet(@RequestParam("id") Integer bookId , Model model, Principal principal){

            Person person = personService.getByEmail(principal.getName());
            Book book = bookService.getBookById(bookId);
            boolean limit = ordersService.isLimitReached(person);
            model.addAttribute("isBookLimitReached", limit);
            if(limit){
               model.addAttribute("inUse", true);
               return "order";
            }
            model.addAttribute("book", book);
            model.addAttribute("orders", ordersService.getOrderByBook(book));
            model.addAttribute("inUse", useService.isPersonHaveBook(person, book));
            model.addAttribute("inOrders", ordersService.isPersonOrderedBook(person, book));
            model.addAttribute("inWishList", wishService.isPersonWishBook(person, book));
            OrderDays minDate = ordersService.getMinOrderDate(book);
            model.addAttribute("minDate", minDate.getMinOrderDate().getTime());
            return "order";

        }

        @RequestMapping(value = "/add", method = RequestMethod.POST)
        @Secured({"ROLE_USER"})
        public ResponseEntity<String> add(@RequestParam("bookId") Integer bookId, @RequestParam("time") Long time, 
                            Principal principal) throws IncorrectInput{
            Book book = bookService.getBookById(bookId);
            Person person = personService.getByEmail(principal.getName());
            Date date = new Date(time);
            OrderDays orderDays;
            try {
                orderDays = ordersService.checkOrderDate(book, date);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            Orders order = new Orders(person, book, date, orderDays.getDaysAvailable());
            ordersService.save(order);
            logger.info("person {} order book {} to date {} for {} days", person, book, date, orderDays.getDaysAvailable());
            mailService.sendMailWithOrder("springytest@gmail.com", "etenzor@gmail.com", "order", order);
            return new ResponseEntity<>(new JSONObject().toString(), HttpStatus.OK);
        }

        @RequestMapping(value = "/my", method = RequestMethod.GET)
        @Secured({"ROLE_USER"})
        public String myG(Model model, Principal principal){
            Person person = personService.getByEmail(principal.getName());
            List<Orders> orders = ordersService.getOrderByPerson(person);
            Map<Orders, OrderDays> ordersMinDates = new HashMap<>();
            for(Orders order : orders){
               ordersMinDates.put(order, ordersService.getMinOrderDate(order.getBook()));  
            }
            model.addAttribute("ordersMinDates", ordersMinDates);
            return "orders";

        }

        @RequestMapping(value = "/delete")
        @Secured({"ROLE_USER"})
        public @ResponseBody String delete(@RequestParam("orderId") Integer orderId){
            ordersService.delete(ordersService.getOrderByID(orderId));
            return new JSONObject().toString();
        }

        @RequestMapping(value = "/edit")
        @Secured({"ROLE_USER"})
        public ResponseEntity<String> edit(@RequestParam("orderId") Integer orderId,
                        @RequestParam("date") Long date, Principal principal) throws IncorrectInput{
            Orders order = ordersService.getOrderByID(orderId);
            Book book = order.getBook();
            Date newDate = new Date(date);
            OrderDays orderDays;
            try {
                orderDays = ordersService.checkOrderDate(book, newDate);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
            order.setOrderDate(newDate);
            order.setDaysAmount(orderDays.getDaysAvailable());
            ordersService.update(order);
            OrderDays minDate = ordersService.getMinOrderDate(book);
            JSONObject json = new JSONObject();
            json.put("orderId", orderId);
            json.put("date", date);
            json.put("minDate", minDate.getMinOrderDate().getTime());
            return new ResponseEntity<>(json.toString(), HttpStatus.OK);
        }

}
