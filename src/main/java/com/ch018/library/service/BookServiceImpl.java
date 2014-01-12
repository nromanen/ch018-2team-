package com.ch018.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ch018.library.DAO.BookDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {

        @Autowired
        private BookDao bookDAO;

        @Autowired
        private BookInUseService useService;


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
        public List<Book> getBooksComplex(String query) {
            return bookDAO.getBooksComplex(query);
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
        public JSONObject getBooksComplexByParamsAsJson(Integer genreId, String Title, String Authors, String Publisher) {
            List<Book> books = bookDAO.getBooksComplexByParams(genreId, Title, Authors, Publisher);
            boolean isUserAuth = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
            return formBooksJsonFromList(books,isUserAuth);
        }

        @Override
        @Transactional
        public JSONObject getBooksComplexAsJson(String query) {

            boolean isUserAuth = SecurityContextHolder.getContext().getAuthentication().isAuthenticated();

            List<Book> books;

            if(query.equals(""))
                books = getAll();
            else
                books = getBooksComplex(query);

            return formBooksJsonFromList(books, isUserAuth);
        }


        private JSONObject formBooksJsonFromList(List<Book> books, boolean isUserAuth){

            List<JSONObject> jsons = new ArrayList<>();

            if(isUserAuth){

                for(Book book : books){
                    JSONObject json = new JSONObject();
                    json.put("bId", book.getbId());
                    json.put("title", book.getTitle());
                    json.put("authors", book.getAuthors());
                    json.put("publisher", book.getPublisher());
                    json.put("description", book.getDescription());
                    json.put("generalQuantity", book.getGeneralQuantity());
                    json.put("currentQuantity", book.getCurrentQuantity());
                    json.put("img", book.getImg());
                    jsons.add(json);
            }
            }else{
                for(Book book : books){
                    JSONObject json = new JSONObject();

                    json.put("title", book.getTitle());
                    json.put("authors", book.getAuthors());
                    json.put("publisher", book.getPublisher());
                    json.put("description", book.getDescription());
                    json.put("generalQuantity", book.getGeneralQuantity());
                    json.put("currentQuantity", book.getCurrentQuantity());
                    json.put("img", book.getImg());
                    jsons.add(json);
            }
            }

            JSONObject finalJson = new JSONObject();
            finalJson.put("auth", isUserAuth);
            finalJson.put("books", jsons);

            return finalJson;

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
}

