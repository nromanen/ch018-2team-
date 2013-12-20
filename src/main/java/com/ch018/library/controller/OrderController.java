
package com.ch018.library.controller;

import java.util.ArrayList;


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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import com.ch018.library.entity.Orders;
import com.ch018.library.service.OrdersService;

@Controller
public class OrderController {

	@Autowired
	private OrdersService orderService;
	
	/**/
	
	@RequestMapping(value = "/")
	public String list(Model model) throws Exception {
		
		model.addAttribute("orders", orderService.getAllOrders());
		Orders order = orderService.getOrderByID(1);
		//System.out.print(order.getBook().getTitle() + " " + order.getPerson().getName());
		
		
		return "orders";
	}
}
