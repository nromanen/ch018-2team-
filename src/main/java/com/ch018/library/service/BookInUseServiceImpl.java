package com.ch018.library.service;

import com.ch018.library.DAO.BooksInUseDao;
import com.ch018.library.DAO.PersonDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Orders;
import com.ch018.library.entity.Person;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author Edd Arazian
 */
@Service
public class BookInUseServiceImpl implements BookInUseService {

      @Autowired
      private BooksInUseDao useDao;
      
      @Autowired
      private PersonService personService;
      
      @Autowired
      private BookService bookService;

      @Autowired
      private PersonDao personDao;
      
      @Autowired
      private OrdersService orderService;
      
      @Autowired
      private MailService mailService;
      
      @Autowired
      private SmsService smsService;
      
      private static final long MILLIS_IN_DAY = 24*3600*1000;
    
      private final Logger logger = LoggerFactory.getLogger(BookInUseServiceImpl.class);

          
    
      @Override
      @Transactional
      public void save(BooksInUse booksInUse) {
        useDao.save(booksInUse);
      }
    
      @Override
      @Transactional
      public void delete(BooksInUse booksInUse) {
        useDao.delete(booksInUse);
      }
    
      @Override
      @Transactional
      public void update(BooksInUse booksInUse) {
        useDao.update(booksInUse);
      }
    
      @Override
      @Transactional
      public List<BooksInUse> getAll() {
        return useDao.getAll();
      }
    
      @Override
      @Transactional
      public List<BooksInUse> getBooksInUseByPerson(Person person) {
        return useDao.getBooksInUseByPerson(person);
      }
    
      @Override
      @Transactional
      public List<BooksInUse> getBooksInUseByBook(Book book) {
        return useDao.getBooksInUseByBook(book);
      }

    
      @Override
      @Transactional
      public List<BooksInUse> getBooksInUseByReturnDate(Date returnDate) {
        return useDao.getBooksInUseByReturnDate(returnDate);
      }
    
      @Override
      @Transactional
      public Date getMinReturnDate(Book book) {
        return useDao.getMinReturnDate(book);
      }
    
      @Override
      public BooksInUse getBookInUseById(int id) {
        return useDao.getBookInUseById(id);
      }
    
      @Override
      @Transactional
      public boolean isPersonHaveBook(Person person, Book book) {
        return useDao.isPersonHaveBook(person, book);
      }
    
      @Override
      @Transactional
      public void getBookBack(BooksInUse bookInUse) {
    
        Date now = new Date();
        List<Orders> orders;
        Person person = bookInUse.getPerson();
        int booksReturnedIntime = person.getTimelyReturn();
        int booksReturnedNotIntime = person.getUntimekyReturn();
        int booksOnHands = 0;
    
        if (now.before(bookInUse.getReturnDate())) {
          booksReturnedIntime += 1;
          person.setTimelyReturn(booksReturnedIntime);
        } else if (now.after(bookInUse.getReturnDate())) {
          booksReturnedNotIntime += 1;
          person.setUntimekyReturn(booksReturnedNotIntime);
        }
    
        booksOnHands = person.getBooksOnHands();
        booksOnHands -= 1;
        person.setBooksOnHands(booksOnHands);
        personService.update(person);
        personService.countRating(person);
        
        Book book = bookInUse.getBook();
        int quantity = book.getCurrentQuantity();
        quantity += 1;
        book.setCurrentQuantity(quantity);
        bookService.update(book);
    
        if((bookInUse.getReturnDate().getTime() - now.getTime()) >= (2*MILLIS_IN_DAY)) {
          orders = orderService.getOrderByBook(book);
          for(Orders order : orders) {
            mailService.sendMailOrderChange("springytest@gmail.com", "etenzor@gmail.com", "Book Available Early", order);
            if(person.isSms()) 
              smsService.sendSms("book: " + order.getBook().getTitle() + " available earlier");
              
            orderService.update(order);
          }
        }
          
        delete(bookInUse);
    
      }
    
      @Override
      @Transactional
      public List<BooksInUse> getBooksInUseByReturnDateLe(Date date) {
        logger.info("in service");
        return useDao.getBooksInUseByReturnDateLe(date);
    
      }

       @Override
       @Transactional
       public List<BooksInUse> getBooksInUseByBookTitle(String title) {
            logger.info("in service");

            List<Book> allBooks = bookService.getBooksByTitle(title);
            List<BooksInUse> allBooksInUse = useDao.getAll();
            List<BooksInUse> answerList = new ArrayList<>();
            for (Book book:allBooks){
                for (BooksInUse booksInUse:allBooksInUse)
                if (book.getbId()==booksInUse.getBook().getbId()) answerList.add(booksInUse);
            }
            return answerList;

        }
        @Override
        @Transactional
        public List<BooksInUse> getBooksInUseByPersonSurname(String surname){
            logger.info("in service");

            List<Person> allPersons = personDao.getPersonsBySurname(surname);
            List<BooksInUse> allBooksInUse = useDao.getAll();
            List<BooksInUse> answerList = new ArrayList<>();
            for (Person person:allPersons){
                for (BooksInUse booksInUse:allBooksInUse)
                    if (person.getPid()==booksInUse.getPerson().getPid()) answerList.add(booksInUse);
            }
            return answerList;
        }
        @Override
        @Transactional
        public List<BooksInUse> getBooksInUseByPersonSurnameAndBookTitle(List<BooksInUse> list,String surname){
            List<BooksInUse> answerList = new ArrayList<>();
            List<BooksInUse> persons = getBooksInUseByPersonSurname(surname);
            for (BooksInUse title:list){
                for (BooksInUse srname:persons )
             if (title.getId()==srname.getId()) answerList.add(srname);
         }
            //System.out.println("!!"+list);
            //System.out.println("!!!"+answerList);
            return answerList;
        }

		@Override
		@Transactional
		public long getBookInUseCount(Book book) {
			return useDao.getBookInUseCount(book);
		}

        
        



}
