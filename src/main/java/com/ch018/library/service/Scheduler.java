package com.ch018.library.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

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
	    
	    @Autowired
	    MailService mailService;
	    
	    @Autowired
	    SmsService smsService;
	
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
	    
	    @Scheduled(cron = "0 29 4 ? * MON-FRI")
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
			        		mailService.sendOrderFail("springytest@gmail.com", "etenzor@gmail.com", "failOrder", order);
			        		if(person.isSms())
			        			smsService.sendSms("You order date for" + order.getBook().getTitle() + " expired and order deleted");
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
    

}
