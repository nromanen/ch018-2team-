package com.ch018.library.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	BookService bookService;
	@Autowired
	GenreService genreService;
	@Autowired
	BookInUseService bookInUseService;
	
	@RequestMapping(value = "")
	public ModelAndView bookList () {
		return new ModelAndView("librarian/books", "books", bookService.getAll());
	}
	
	@RequestMapping(value= "/addbook", method = RequestMethod.GET)
        public String add(Model model) throws Exception {
		final int DEFAULT_TERM_OF_ISSUANCE = 14;
		Book book = new Book();
		book.setTerm(DEFAULT_TERM_OF_ISSUANCE);
		model.addAttribute("book", book);
		model.addAttribute("genre", genreService.getAll());
		return "librarian/addbook";
	}
	
	@RequestMapping(value= "/addbook", method = RequestMethod.POST)
	public String add(@ModelAttribute("book") @Valid Book book, BindingResult result, @RequestParam("genreId") Integer gid) throws Exception {
		Genre genre = genreService.getById(gid);
		book.setGenre(genre);
		if (result.hasErrors()) {
			System.out.println("Errors Addind Book" + result.toString());
		}else {
			bookService.save(book);
		}
		return "redirect:/librarian/books";
	}
	
	
	@RequestMapping(value = "/deletebook")
	public String deleteBook(@RequestParam("id") int bookId, Model model) throws SQLException {
		Book book = bookService.getBookById(bookId);
		bookService.delete(book);
		return "redirect:/librarian/books";
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.GET)
	public String edit(@RequestParam("id") int bookId, Model model) throws SQLException {
		model.addAttribute("genre", genreService.getAll());
		model.addAttribute("book", bookService.getBookById(bookId));
		return "librarian/editbook";
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.POST)
	public String edit(@ModelAttribute("book") Book book, BindingResult result, @RequestParam("id") int bookId,
					   @RequestParam("genreId") Integer gid, Model model) throws Exception {
		book.setGenre(genreService.getById(gid));
		bookService.update(book);
		return "redirect:/librarian/books";
	}
	
	@RequestMapping(value = "/advancedsearch", method = RequestMethod.GET)
	public String advancedSearch(Model model) throws Exception {
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("genre", genreService.getAll());
		return "librarian/bookadvancedsearch";
	}
	
	@RequestMapping(value = "/advancedsearch", method = RequestMethod.POST)
	public String advancedSearch(@ModelAttribute("book") Book book, BindingResult result, @RequestParam("genreId") Integer gid, Model model) throws Exception {
		Genre genre = genreService.getById(gid);
		book.setGenre(genre);
		List<Book> books = bookService.advancedSearch(book);
		model.addAttribute("books", books);
		return "librarian/booksearchresult";
	}
	
	@RequestMapping(value = "/holders", method = RequestMethod.GET)
	public String showBookHolders(@RequestParam("id") int id, Model model) throws Exception {
		Book book = new Book();
		book = bookService.getBookById(id);
		Map<BooksInUse, Integer> booksInUse = bookService.getHolders(bookService.getBookById(id));
		model.addAttribute("book", book);
		model.addAttribute("booksInUse", booksInUse);
		return "librarian/bookholders";
	}
	
	@RequestMapping(value = "/simplesearch", method=RequestMethod.GET)
	public String simpleSearch(Model model) {
		return "librarian/books";
	}
	
	@RequestMapping(value = "/simplesearch", method=RequestMethod.POST)
	public String simpleSearch(@RequestParam("request") String request, Model model) throws Exception {
		List<Book> books = bookService.simpleSearch(request);
		model.addAttribute("books", books);
		return "librarian/booksearchresult";
	}
}
