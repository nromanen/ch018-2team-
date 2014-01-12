package com.ch018.library.service;

import java.util.Date;

import com.ch018.library.entity.Orders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
/**
 *
 * @author Edd Arazian
 */
@Service
public class MailService {
    
        @Autowired
        private MailSender mailSender;
        
        private final Logger logger = LoggerFactory.getLogger(MailService.class);
        
        public void sendMail(String from, String to, String subject, String body) {
            logger.info("start mail forming");
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            logger.info("before mail sending");
            mailSender.send(message);
            logger.info("mail sended");
        }
        
        public void sendMailWithOrder(String from, String to, String subject, Orders order) {
            StringBuilder body = new StringBuilder("Congratulations! ");
            body.append(order.getPerson().getName());
            body.append(" you succesfully ordered book: ");
            body.append(order.getBook().getTitle());
            body.append(" and you can get it : ");
            body.append(order.getOrderDate());
            sendMessage(from, to, subject, body.toString());
          
        }
        
        public void sendMailOrderChange(String from, String to, String subject, Orders order) {
            StringBuilder body = new StringBuilder("Book: ");
            body.append(order.getBook().getTitle());
            body.append(" which you order on: ");
            body.append(order.getOrderDate());
            body.append(" not available please change date in your orders: ");
            body.append(order.getOrderDate());
            sendMessage(from, to, subject, body.toString());
        }
        
        private void sendMessage(final String from, final String to, final String subject, final String body) {
            
            Runnable send = new Runnable() {
                @Override
                public void run() {
                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setFrom(from);
                    message.setTo(to);
                    message.setSubject(subject);
                    message.setText(body);
                    mailSender.send(message);
                }
            };
            
            Thread sendThread = new Thread(send);
            sendThread.start();   
        }
}
