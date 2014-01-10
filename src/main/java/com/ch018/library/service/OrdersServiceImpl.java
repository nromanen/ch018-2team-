package com.ch018.library.service;

import com.ch018.library.DAO.BooksInUseDao;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;




import com.ch018.library.DAO.OrdersDao;
import com.ch018.library.DAO.OrdersDaoImpl;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;
import java.util.ArrayList;

@Service
public class OrdersServiceImpl implements OrdersService{


        @Autowired
        OrdersDao ordersDao;
        
        @Autowired
        WishListService wishService;
        
        @Autowired
        BooksInUseDao useDao;
        
        @Autowired
        MailService mailService;
       
		@Autowired
        PersonService personService;
        
        @Autowired
        BookService bookService;
        
        @Autowired
        BookInUseService booksInUseService;
        
        @Override
        @Transactional
        public void save(Orders order){
                
                ordersDao.save(order);
                if(wishService.isPersonWishBook(order.getPerson(), order.getBook()))
                    wishService.delete(wishService.getWishByPersonBook(order.getPerson(), order.getBook()));
        }

        @Override
        @Transactional
        public void delete(Orders order){
                // TODO Auto-generated method stub
                ordersDao.delete(order);
        }

        @Override
        @Transactional
        public void update(int id, Date newDate){
                // TODO Auto-generated method stub
                ordersDao.update(id, newDate);
        }

        @Override
        @Transactional
        public List<Orders> getAll(){
                // TODO Auto-generated method stub
                return ordersDao.getAll();
        }

        @Override
        @Transactional
        public List<Orders> getOrderByPerson(Person person){
                // TODO Auto-generated method stub
                return ordersDao.getOrderByPerson(person);
        }

        @Override
        @Transactional
        public List<Orders> getOrderByBook(Book book){
                // TODO Auto-generated method stub
                return ordersDao.getOrderByBook(book);
        }

        @Override
        @Transactional
        public List<Orders> getOrderByDate(Date date){
                // TODO Auto-generated method stub
                return ordersDao.getOrderByDate(date);
        }

        @Override
        @Transactional
        public Orders getOrderByID(int id){
                // TODO Auto-generated method stub
                return ordersDao.getOrderByID(id);
        }



       

		@Override
		public List<Orders> getOrdersToday() {
			// TODO Auto-generated method stub
			return ordersDao.getOrdersToday();
		}

		@Override
		public List<Orders> getOrdersInHour() {
			// TODO Auto-generated method stub
			return ordersDao.getOrdersInHour();
		}

    @Override
    @Transactional
    public int getBookIdByPerson(Person person) {
        return ordersDao.getBookIdByPerson(person);
    }

    @Override
    @Transactional
    public boolean isPersonOrderedBook(Person person, Book book) {
        return ordersDao.isPersonOrderedBook(person, book);
    }

    @Override
    @Transactional
    public boolean isLimitReached(Person person){
        int ordersCount = ordersDao.getOrderByPerson(person).size();
        int useCount = useDao.getBooksInUseByPerson(person).size();
        
        if(person.getMultiBook() > ordersCount + useCount)
            return false;
        else 
            return true;
    }

    @Override
    @Transactional
    public void checkPersonOrders(Book book, Date returnDate) {
        List<Orders> orders;
        if(book.getCurrentQuantity() <= 0){
            orders = ordersDao.getOrdersForChanging(book, returnDate);
            if(orders != null){
                for(Orders order : orders){
                    order.setChanged(Boolean.TRUE);
                    ordersDao.update(order);
                    mailService.sendMailOrderChange("springytest@gmail.com", "etenzor@gmail.com", "order changed", order); 
                }
            }
        }
    }


	@Override
	@Transactional
	public void issue(Orders order) {
		// TODO Auto-generated method stub
		
		Person person = order.getPerson();
		
		int booksOnHands = person.getBooksOnHands();
		
		person.setBooksOnHands(++booksOnHands);
		
		personService.update(person);
		
		Book book = order.getBook();
		
		int currentQuantity = book.getCurrentQuantity();
		
		currentQuantity -=1;
		
		book.setCurrentQuantity(currentQuantity);
		
		bookService.update(book);
		
		BooksInUse  bookInUse = new BooksInUse();
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, +14);
		Date date = calendar.getTime();
		
		bookInUse.setBook(order.getBook());
		bookInUse.setPerson(order.getPerson());
		bookInUse.setReturnDate(date);
		
		booksInUseService.save(bookInUse);
	}
    
}