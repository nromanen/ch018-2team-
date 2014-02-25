package com.ch018.library.service;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

/**
 * 
 * @author Edd Arazian
 */
@Service
public class MailService {
	
		@Autowired
		private MailSender mailSender;
		
		@Autowired
		private ServletContext servletContext;
	
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
	
		public void sendMailWithOrder(String from, String to, String subject,
				Orders order) {
			StringBuilder body = new StringBuilder("Congratulations! ");
			body.append(order.getPerson().getName());
			body.append(" you succesfully ordered book: ");
			body.append(order.getBook().getTitle());
			body.append(" and you can get it : ");
			body.append(order.getOrderDate());
			sendMessage(from, to, subject, body.toString());
	
		}
	
		public void sendMailOrderChange(String from, String to, String subject,
				Orders order) {
			StringBuilder body = new StringBuilder("Book: ");
			body.append(order.getBook().getTitle());
			body.append(" which you order on: ");
			body.append(order.getOrderDate());
			body.append(" available for early date you can change date in your orders: ");
			sendMessage(from, to, subject, body.toString());
		}
	
		public void sendConfirmationMail(String from, String to, String subject,
				String key, String path) {
			StringBuilder body = new StringBuilder(
					"For account confirmation please click here : ");
			body.append(path);
			body.append("/confirm?key=");
			body.append(key);
			sendMessage(from, to, subject, body.toString());
		}
	
		public void sendRestoreMail(String from, String to, String key, String path) {
			String subject = "password restore details";
			StringBuilder body = new StringBuilder(
					"For password restore please click link : ");
			body.append(path);
			body.append("/restore/password?key=");
			body.append(key);
			logger.info("path = {}", path + "/restore/password?key=" + key);
			sendMessage(from, to, subject, body.toString());
		}
		
		public void sendOrderFail(String from, String to, String subject, Orders order) {
			StringBuilder body = new StringBuilder("Order for: ");
			body.append(order.getBook().getTitle());
			body.append(" deleted, because order date expired");
			sendMessage(from, to, subject, body.toString());
		}
	
		private void sendMessage(final String from, final String to,
				final String subject, final String body) {
	
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
	
		public void sendEmailBookIssued(String from, String to, String subject,
										Orders order, Person person, Book book, BooksInUse bookinuse, int term) {
	
			StringBuilder letter = new StringBuilder();
			letter.append("Dear ").append(person.getName())
					.append(" . You have recieved a book ")
					.append(book.getTitle() + " " + book.getAuthors())
					.append(" on " + order.getOrderDate())
					.append("for " + term + " days")
					.append(". Please turn it back on ")
					.append(bookinuse.getReturnDate())
					.append(". Best regards, your library!");
	
			sendMessage(from, to, subject, letter.toString());
		}

}
