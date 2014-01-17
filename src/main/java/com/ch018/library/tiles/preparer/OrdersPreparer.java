/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.tiles.preparer;

import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PersonService;
import java.util.List;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Admin
 */
@Component(value = "orders")
public class OrdersPreparer implements ViewPreparer {

    @Autowired
    OrdersService ordersService;
    
    @Autowired
    PersonService personService;
    
    @Override
    public void execute(Request rqst, AttributeContext ac) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Person person = personService.getByEmail(email);
        List<Orders> orders = ordersService.getOrderByPerson(person);
        int count = orders == null ? 0 : orders.size();
        ac.putAttribute("orders", new Attribute(count));
    }
    
    
    
}
