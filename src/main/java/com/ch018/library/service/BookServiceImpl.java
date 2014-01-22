package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch018.library.DAO.BookDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.helper.BookSearch;
import com.ch018.library.helper.Page;
import com.ch018.library.validation.BookEditValidator;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

        @Autowired
        private BookDao bookDAO;

        @Autowired
        private BookInUseService useService;
        
        @Autowired
    	private GenreService genreService;

        	
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
        @Transactional
        public Page getBooksComplex(BookSearch bookSearch) throws Exception {
            Page books = bookDAO.getBooksComplex(bookSearch);
            return books;
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
        public Page getBooksComplexByParams(BookSearch bookSearch) {
            Page books = bookDAO.getBooksComplexByParams(bookSearch);
            return books;
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
		public void update(BookEditValidator book, int genreId) {
			
			Book bookEdit;
			bookEdit = getBookById(book.getbId());
			
			bookEdit.setTitle(book.getTitle());
			bookEdit.setAuthors(book.getAuthors());
			bookEdit.setYear(book.getYear());
			bookEdit.setPublisher(book.getPublisher());
			bookEdit.setPages(book.getPages());
			bookEdit.setDescription(book.getDescription());
			bookEdit.setImg(book.getImg());
			bookEdit.setShelf(book.getShelf());
			bookEdit.setTerm(book.getTerm());
			bookEdit.setGeneralQuantity(book.getGeneralQuantity());
			bookEdit.setGenre(genreService.getById(genreId));
			
			update(bookEdit);
		}
}

