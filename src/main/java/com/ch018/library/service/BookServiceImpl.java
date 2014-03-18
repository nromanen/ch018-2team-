package com.ch018.library.service;

import com.ch018.library.DAO.BookDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;
import com.ch018.library.util.Constants;
import com.ch018.library.util.DataModelContainer;
import com.ch018.library.util.Switch;
import org.apache.mahout.cf.taste.impl.recommender.CachingRecommender;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import com.ch018.library.DAO.BookDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;
import com.ch018.library.util.Constants;
import com.ch018.library.util.DataModelContainer;
import com.ch018.library.util.Switch;

@Service
public class BookServiceImpl implements BookService {

		@Autowired
        private BookDao bookDAO;

        @Autowired
        private BookInUseService useService;
        
        @Autowired
    	private GenreService genreService;
        
        @Autowired
        private PersonService personService;
        
        @Autowired
        private DataModelContainer dataModelContainer;
        
        @Autowired
        private RateService rateService;
        
        @Autowired
        private Switch switcher;
        	
        private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

        @Override
        @Transactional
        public void save(Book book) {
            bookDAO.save(book);
        }

        @Override
        @Transactional
        public void delete(Book book) {
            bookDAO.delete(book);
        }

        @Override
        @Transactional
        public void update(Book book) {
            bookDAO.update(book);
        }

        @Override
        @Transactional
        public void update(Book book, Integer gid) {
        	Genre genre = genreService.getById(gid);
        	Book originalBook = getBookById(book.getbId());
        	book.setCurrentQuantity(originalBook.getCurrentQuantity());
        	book.setShelf(originalBook.getShelf());
        	book.setGenre(genre);
        	bookDAO.update(book);
        }
        
        @Override
        @Transactional
        public List<Book> getAll() {
            return bookDAO.getAll();
        }

        @Override
        @Transactional
        public Book getBookById(int id) {

            return bookDAO.getBookById(id);
        }

        @Override
        @Transactional
        public List<Book> getBooksByTitle(String title) {
            return bookDAO.getBooksByTitle(title);
        }

        @Override
        @Transactional
        public List<Book> getBooksByAuthors(String authors) {
            return bookDAO.getBooksByAuthors(authors);
        }

        @Override
        @Transactional
        public List<Book> getBooksByYear(int year) {
            return bookDAO.getBooksByYear(year);
        }

        @Override
        @Transactional
        public List<Book> getBooksByPublisher(String publisher) {
            return bookDAO.getBooksByPublisher(publisher);
        }

        @Override
        @Transactional
        public List<Book> getBooksByPagesEq(int pages) {
            return bookDAO.getBooksByPagesEq(pages);
        }

        @Override
        @Transactional
        public List<Book> getBooksByGenre(Genre genre) {
            return bookDAO.getBooksByGenre(genre);
        }

		@Override
		public List<Book> advancedSearch(Book book) {
			return bookDAO.advancedSearch(book);
		}

		@Override
		public List<Book> simpleSearch(String query) {
			return bookDAO.simpleSearch(query);
		}

        @Override
        @Transactional
        public Map<BooksInUse, Integer> getHolders(Book book) {
            List<BooksInUse> booksInUse = useService.getBooksInUseByBook(book);
            Map<BooksInUse, Integer> booksInUseEx = new HashMap<BooksInUse, Integer>();

            Date date = new Date();
            Calendar currentDate = Calendar.getInstance();
            Calendar endDate = Calendar.getInstance();
            currentDate.setTime(date);
            int difference;

            for (BooksInUse booksInUse2 : booksInUse) {

                endDate.setTime(booksInUse2.getReturnDate());
                difference = endDate.get(Calendar.DAY_OF_YEAR) - currentDate.get(Calendar.DAY_OF_YEAR);
                booksInUseEx.put(booksInUse2, difference);
            }


            return booksInUseEx;
        }

		@Override
		@Transactional
		public Integer getMinIntegerField(String field) {
			return bookDAO.getMinIntegerField(field);
		}

		@Override
		@Transactional
		public Integer getMaxIntegerField(String field) {
			return bookDAO.getMaxIntegerField(field);
		}

		@Override
		@Transactional
		public List<Book> getLastByField(String field, int quantity) {
			return bookDAO.getLastByField(field, quantity);
		}

		@Override
		@Transactional
		public List<Book> getRecommended(int quantity) {
			List<Book> books = new ArrayList<>();
			List<RecommendedItem> items = new ArrayList<>();
			CachingRecommender cache;
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			Person person = personService.getByEmail(email);
			
			if(person == null) {
				return getLastByField("rating", quantity);
			}
			
			if(switcher.getRecommendationState() && rateService.getRatesCount() > Constants.MIN_COUNT_FOR_RECOMMEND) {
				dataModelContainer.initDataModel();
				cache = dataModelContainer.getCachedRecommender();
				try {
					items = cache.recommend(person.getPid(), quantity);
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
				
			}
			
			if(items.isEmpty()) {
				books = getLastByField("rating", quantity);
			} else {
				books = bookDAO.getBooksFromRecommendedList(items);
			}
			
			return books;
			
		}

		@Override
		@Transactional
		public long getBooksCount() {
			return bookDAO.getBooksCount();
		}
		@Override
        @Transactional
        public List<Book> getAllPagin(int n){
            return bookDAO.getAllPagin(n);
        }
		

		
		
}

