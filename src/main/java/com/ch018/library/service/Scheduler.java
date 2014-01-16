package com.ch018.library.service;

import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import java.util.Date;
import java.util.List;
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
    PersonService personService;
    
    @Scheduled(cron = "0 10 15 ? * MON-FRI")
    public void checkUsers(){
        Runnable r = new Runnable() {

            @Override
            public void run() {
            
                logger.info("in run befor {}", useService);
        Date today = new Date();
        List<BooksInUse> untimelyUses = useService.getBooksInUseByReturnDateLe(today);
        logger.info("in run {}", untimelyUses);
        if(untimelyUses != null){
            for (BooksInUse use : untimelyUses) {
                logger.info("untimelyReturn user {}", use.getPerson());
                Person person = use.getPerson();
                if(person.getGeneralRating() > 0) {
                    person.setGeneralRating(person.getGeneralRating() - getExpiredDays(use.getReturnDate())*(24*3600*1000));
                    if(person.getGeneralRating() < 0)
                        person.setGeneralRating(0);
                    personService.update(person);
                }
            }
        }
            }
        };
        Thread thread = new Thread(r);
        thread.start();
        logger.info("Thread for untimelyReturn check started at {}", new Date());
    }
    
    private long getExpiredDays(Date date) {
        Date today = new Date();
        long exp = (today.getTime() - date.getTime())/(24*3600*1000);
        return exp;
    }
    
}
