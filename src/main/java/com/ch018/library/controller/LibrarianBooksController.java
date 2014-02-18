package com.ch018.library.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;

@Controller
@RequestMapping(value = "/librarian/books")
public class LibrarianBooksController {
		
		@Autowired
		private BookService bookService;
		
		@Autowired
		private GenreService genreService;
		
		@Autowired
		private BookInUseService bookInUseService;
		

		
		final Logger logger = LoggerFactory.getLogger(LibrarianBooksController.class);
		
		private Locale locale;
		
		@RequestMapping(value = "")
		public String bookList(Model model) {
			
			locale = LocaleContextHolder.getLocale();
			
			model.addAttribute("books", bookService.getAll());
			return "librarian_books";
		}
		
		@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	        public String add(Model model) throws Exception {
			final int DEFAULT_TERM_OF_ISSUANCE = 14;
			Book book = new Book();
			book.setTerm(DEFAULT_TERM_OF_ISSUANCE);
			
			Locale locale = LocaleContextHolder.getLocale();
			
			model.addAttribute("book", book);
			model.addAttribute("genres", genreService.getAll());
			return "librarian_books_add_book";
		}
		
		@RequestMapping(value = "/addbook", method = RequestMethod.POST)
		public String add(@ModelAttribute @Valid Book book, BindingResult result,
							@RequestParam("gid") Integer gid, Model model) throws Exception {
			//Set<GenreTranslations> genreTranslation = genreTranslService.getByGenreId(gid);
			//book.setGenre(genreTranslation);
			book.setGenre(genreService.getById(gid));
			if (result.hasErrors()) {
				model.addAttribute("genre", genreService.getAll());
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
			
			//locale = LocaleContextHolder.getLocale();
			
			Book book = bookService.getBookById(bookId);
			
			//model.addAttribute("genres", genreTranslService.getAllByLocale(locale.toString()));
			model.addAttribute("book", bookService.getBookById(bookId));
			model.addAttribute("genres", genreService.getAll());
			return "librarian_books_edit_book";
		}
		
		@RequestMapping(value = "/editbook", method = RequestMethod.POST)
		public String edit(@RequestParam("gid") Integer gid, @Valid @ModelAttribute  Book book, BindingResult result,
						    Model model) throws Exception {
			Genre genre = genreService.getById(gid);
			book.setGenre(genre);
			if (result.hasErrors()) {
				model.addAttribute("genres", genreService.getAll());
				logger.info("Error editing book: " + result.toString());
				return "librarian_books_edit_book";
			} else {
				bookService.update(book);
			}
			return "redirect:/librarian/books";
		}
		
		@RequestMapping(value = "/advancedsearch", method = RequestMethod.GET)
		public String advancedSearch(Model model) throws Exception {
			locale = LocaleContextHolder.getLocale();
			Book book = new Book();
			model.addAttribute("book", book);
			model.addAttribute("genre", genreService.getAll());
			return "librarian_books_advanced_search";
		}
		
		@RequestMapping(value = "/advancedsearch", method = RequestMethod.POST)
		public String advancedSearch(@ModelAttribute("book") Book book, BindingResult result, @RequestParam("genreId") Integer gid, Model model) throws Exception {
			
			locale = LocaleContextHolder.getLocale();
			
			List<Book> booksList = bookService.advancedSearch(book);
			
			//HashMap<Book, String> books = bookService.getBooksByLocale(booksList, locale);
			
			if (booksList.size() > 0) {
				model.addAttribute("books", booksList);
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
			
			//HashMap<Book, String> books = bookService.getBooksByLocale(booksList, locale);
			
			if (booksList.size() > 0) {
				model.addAttribute("books", booksList);
			} else {
				model.addAttribute("books", bookService.getAll());
			}
			
			return "librarian_books";
		}
}
