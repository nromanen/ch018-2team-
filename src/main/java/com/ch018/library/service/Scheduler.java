package com.ch018.library.service;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author Edd Arazian
 */
@Component
public class Scheduler {
    
    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);
    
    @Autowired
    BookInUseService useService;
    
    @Autowired
    OrdersService orderService;
    
    @Autowired
    PersonService personService;
    

    @Scheduled(cron = "0 12 1 ? * MON-FRI")
    public void checkUsers(){
        Runnable r = new Runnable() {

			@Override
			public void run() {

				logger.info("in run befor {}", useService);
				Date today = new Date();
				List<BooksInUse> untimelyUses = useService
						.getBooksInUseByReturnDateLe(today);
				logger.info("in run {}", untimelyUses);
				if (untimelyUses != null) {
					for (BooksInUse use : untimelyUses) {
						logger.info("untimelyReturn user {}", use.getPerson());
						Person person = use.getPerson();
						if (person.getGeneralRating() > 0) {
							person.setGeneralRating(person.getGeneralRating() - 5);
							if (person.getGeneralRating() < 0)
								person.setGeneralRating(0);
							personService.update(person);
						}
					}
				}
				logger.info("Thread for untimelyReturn check finished at {}", new Date());
			}
        };
        Thread thread = new Thread(r);
        thread.start();
        logger.info("Thread for untimelyReturn check started at {}", new Date());
    }
    
    @Scheduled(cron = "0 21 14 ? * MON-FRI")
    public void checkOrders(){
        Runnable r = new Runnable() {

            @Override
            public void run() {
            	
		        logger.info("in run befor {}", useService);
		        List<Orders> orders = orderService.getOrdersToday();
		        if(orders != null){
		        	for(Orders order : orders) {
		        		Person person = order.getPerson();
		        		int failed = person.getFailedOrders();
		        		person.setFailedOrders(++failed);
		        		personService.countRating(person);
		        		orderService.delete(order);
		        		logger.info("Order {} deleted", order);
		        	}
		        }
		        logger.info("Thread for failed orders check finished at {}", new Date());
           }
        };
        Thread thread = new Thread(r);
        thread.start();
        logger.info("Thread for failed orders check started at {}", new Date());
    }
    
    private long getExpiredDays(Date date) {
        Date today = new Date();
        long exp = (today.getTime() - date.getTime())/(24*3600*1000);
        return exp;
    }
    
}
