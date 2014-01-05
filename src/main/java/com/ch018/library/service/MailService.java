/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.service;

import com.ch018.library.entity.Orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
/**
 *
 * @author Admin
 */
@Service
public class MailService {
    
        @Autowired
        MailSender mailSender;
        
        final Logger logger = LoggerFactory.getLogger(MailService.class);
        
        public void sendMail(String from, String to, String subject, String body){
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
        
        public void SendMailWithOrder(String from, String to, String subject, Orders order){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            StringBuilder body = new StringBuilder("Congratulations! ");
            body.append(order.getPerson().getName());
            body.append(" you succesfully ordered book: ");
            body.append(order.getBook().getTitle());
            body.append(" and you can get it : ");
            body.append(order.getOrderDate());
            message.setText(body.toString());
            mailSender.send(message);
        }
 
    
}
