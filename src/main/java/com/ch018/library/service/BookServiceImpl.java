package com.ch018.library.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ch018.library.DAO.BookDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.GenreTranslations;
import com.ch018.library.helper.SearchParams;
import com.ch018.library.validation.BookEditValidator;

@Service
public class BookServiceImpl implements BookService {

        @Autowired
        private BookDao bookDAO;

        @Autowired
        private BookInUseService useService;
        
        @Autowired
    	private GenreService genreService;
        
        @Autowired
		private GenreTranslationService genreTranslService;

        	
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
        public List<Book> getBooksComplex(SearchParams searchParams) {
        	List<Book> books = bookDAO.getBooksComplex(searchParams);
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
		public void update(Book book, int genreId) {
			
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
			bookEdit.setGenre(genreTranslService.getByGenreId(genreId));
			
			update(bookEdit);
		}

		@Override
		@Transactional
		public HashMap<Book, String> getAllByLocale(Locale locale) {
			
			HashMap<Book, String> localBooks = new HashMap<>();
			List<Book> book = getAll();
			Set<GenreTranslations> genre;
			
			for (Book bk : book) {
				genre = bk.getGenre();
				localBooks.put(bk, genreTranslService.getDescription(bk, locale));
			}
			
			return localBooks;
		}

		@Override
		@Transactional
		public HashMap<Book, String> getBooksByLocale(List<Book> book,
				Locale locale) {
			
			HashMap<Book, String> localBooks = new HashMap<>();
			Set<GenreTranslations> genre;
			
			for (Book bk : book) {
				genre = bk.getGenre();
				localBooks.put(bk, genreTranslService.getDescription(bk, locale));
			}
			
			return localBooks;
		}
		
		@Override
		public HashMap<Book, String> getBookByLocale(Book book, Locale locale) {
			
			HashMap<Book, String> localBook = new HashMap<>();
			localBook.put(book, genreTranslService.getDescription(book, locale));
			
			return localBook;
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

		
}

