package com.ch018.library.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.GenreTranslations;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.GenreTranslationService;
import com.ch018.library.validation.BookEditValidator;

@Controller
@RequestMapping(value = "/librarian/books")
public class LibrarianBooksController {
		
		@Autowired
		private BookService bookService;
		
		@Autowired
		private GenreService genreService;
		
		@Autowired
		private BookInUseService bookInUseService;
		
		@Autowired
		private GenreTranslationService genreTranslService;
		
		final Logger logger = LoggerFactory.getLogger(LibrarianBooksController.class);
		
		private Locale locale;
		
		@RequestMapping(value = "")
		public String bookList(Model model) {
			
			locale = LocaleContextHolder.getLocale();
			
			model.addAttribute("books", bookService.getAllByLocale(locale));
			return "librarian_books";
		}
		
		@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	        public String add(Model model) throws Exception {
			final int DEFAULT_TERM_OF_ISSUANCE = 14;
			Book book = new Book();
			book.setTerm(DEFAULT_TERM_OF_ISSUANCE);
			
			Locale locale = LocaleContextHolder.getLocale();
			
			model.addAttribute("book", book);
			model.addAttribute("genre", genreTranslService.getAllByLocale(locale.toString()));
			return "librarian_books_add_book";
		}
		
		@RequestMapping(value = "/addbook", method = RequestMethod.POST)
		public String add(@ModelAttribute("book") @Valid Book book, BindingResult result, @RequestParam("genreId") Integer gid, Model model) throws Exception {
			Set<GenreTranslations> genreTranslation = genreTranslService.getByGenreId(gid);
			book.setGenre(genreTranslation);
			if (result.hasErrors()) {
				model.addAttribute("genre", genreTranslService.getAllByLocale(locale.toString()));
				logger.info("Error Addind Book" + result.toString());
				return "librarian_books_add_book";
			} else {
				book.setCurrentQuantity(book.getGeneralQuantity());
				bookService.save(book);
			}
			return "redirect:/librarian/books";
		}
		
		
		@RequestMapping(value = "/deletebook")
		public String deleteBook(@RequestParam("id") int bookId, Model model) throws SQLException {
		
			String error = "error";
			
			try {
				Book book = bookService.getBookById(bookId);
				bookService.delete(book);
				logger.info("Book deleted");
			} catch (Exception e) {
				System.out.println("Error deleting books");
			}
			return "redirect:/librarian/books";
		}
		
		@RequestMapping(value = "/editbook", method = RequestMethod.GET)
		public String edit(@RequestParam("id") int bookId, Model model) throws SQLException {
			
			locale = LocaleContextHolder.getLocale();
			
			Book book = bookService.getBookById(bookId);
			
			model.addAttribute("genre", genreTranslService.getAllByLocale(locale.toString()));
			model.addAttribute("book", bookService.getBookById(bookId));
			return "librarian_books_edit_book";
		}
		
		@RequestMapping(value = "/editbook", method = RequestMethod.POST)
		public String edit(@ModelAttribute("book") @Valid BookEditValidator book, BindingResult result, @RequestParam("id") int bookId,
						   @RequestParam("genreId") Integer gid, Model model) throws Exception {
			
			if (result.hasErrors()) {
				model.addAttribute("genre", genreService.getAll());
				logger.info("Error editing book: " + result.toString());
				return "librarian_books_edit_book";
			} else {
				bookService.update(book, gid);
			}
			//book.setGenre(genreService.getById(gid));
			return "redirect:/librarian/books";
		}
		
		@RequestMapping(value = "/advancedsearch", method = RequestMethod.GET)
		public String advancedSearch(Model model) throws Exception {
			locale = LocaleContextHolder.getLocale();
			Book book = new Book();
			model.addAttribute("book", book);
			model.addAttribute("genre", genreTranslService.getAllByLocale(locale.toString()));
			return "librarian_books_advanced_search";
		}
		
		@RequestMapping(value = "/advancedsearch", method = RequestMethod.POST)
		public String advancedSearch(@ModelAttribute("book") Book book, BindingResult result, @RequestParam("genreId") Integer gid, Model model) throws Exception {
			
			locale = LocaleContextHolder.getLocale();
			
			Set<GenreTranslations> genreTranslations = genreTranslService.getByGenreId(gid);
			book.setGenre(genreTranslations);
			
			List<Book> booksList = bookService.advancedSearch(book);
			
			HashMap<Book, String> books = bookService.getBooksByLocale(booksList, locale);
			
			if (books.size() > 0) {
				model.addAttribute("books", books);
			} else {
				model.addAttribute("books", bookService.getAll());
			}
			
			return "librarian_books";
		}
		
		@RequestMapping(value = "/holders", method = RequestMethod.GET)
		public String showBookHolders(@RequestParam("id") int id, Model model) throws Exception {
			Book book = new Book();
			book = bookService.getBookById(id);
			Map<BooksInUse, Integer> booksInUse = bookService.getHolders(bookService.getBookById(id));
			model.addAttribute("book", book);
			model.addAttribute("booksInUse", booksInUse);
			return "librarian_books_holders";
		}
		
		@RequestMapping(value = "/simplesearch", method=RequestMethod.GET)
		public String simpleSearch(Model model) {
			return "librarian/books";
		}
		
		@RequestMapping(value = "/simplesearch", method=RequestMethod.POST)
		public String simpleSearch(@RequestParam("request") String request, Model model) throws Exception {
			
			if (request.equals("")) {
				model.addAttribute("books", bookService.getAll());
				return "librarian_books";
			}
			
			List<Book> booksList = bookService.simpleSearch(request);
			
			HashMap<Book, String> books = bookService.getBooksByLocale(booksList, locale);
			
			if (books.size() > 0) {
				model.addAttribute("books", books);
			} else {
				model.addAttribute("books", bookService.getAll());
			}
			
			return "librarian_books";
		}
}
