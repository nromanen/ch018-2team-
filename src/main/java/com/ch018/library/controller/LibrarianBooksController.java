package com.ch018.library.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
		
		return new ModelAndView("librarianbooks", "books", bookService.getAll());
	}
	
	@RequestMapping(value= "/addbook", method = RequestMethod.GET)
	public String add(Model model) throws Exception {
		Book book = new Book();
		model.addAttribute("book", book);
		model.addAttribute("genre", genreService.getAll());
		return "librarianaddbook";
	}
	
	@RequestMapping(value= "/addbook", method = RequestMethod.POST)
	public String add(@ModelAttribute("book") Book book, BindingResult result, @RequestParam("genreId") Integer gid) throws Exception {
		Genre genre = genreService.getById(gid);
		System.out.println("Genre Id = " + gid);
		book.setGenre(genre);
		System.out.println("Book title = " + book.getTitle());
		bookService.save(book);
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
		System.out.println("Book " + bookService.getBookById(bookId));
		return "librarianeditbook";
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.POST)
	public String edit(@ModelAttribute("book") Book book, BindingResult result, @RequestParam("id") int bookId,
					   @RequestParam("genreId") Integer gid, Model model) throws Exception {
		
		System.out.println("Book ID = " + bookId + "++++++");
		System.out.println("Genre ID = " + gid + " " + genreService.getById(gid));
		book.setGenre(genreService.getById(gid));
		bookService.update(book);
		
		return "redirect:/librarian/books";
	}
	
	
	
	//ToReturn*all time*(Unfinished)
	@RequestMapping(value = "/toreturn")
	public String toReturn(Model model) throws SQLException {
			
		/*List<Date> result = bookInUseService.getBooksInUseToReturnDate();
		List<BooksInUse> biu = new ArrayList<BooksInUse>();
		ArrayList<Book> books = new ArrayList<Book>();
		
		for (Date date : result) {
			biu = bookInUseService.getBooksInUseByReturnDate(date);
			System.out.println("The date is: " + date);
			for (BooksInUse booksInUse : biu) {
				books.add(booksInUse.getBook());
			}
		}
			
		for (Book book : books) {
			System.out.println("Book is: " + book.getAuthors() + " " +book.getTitle());
		}*/
		
		//model.addAttribute("books", books);
		model.addAttribute("booksInUse", bookInUseService.getAll());

		return "librarianbookstoreturn";
	}
	//Is not finished, but date comparison works fine
	@RequestMapping(value = "/toreturntoday")
	public String toReturnToday(Model model) throws Exception {
		
		Date date = new Date();

		List<Date> result = bookInUseService.getBooksInUseToReturnDate();
		Date bookDate = result.get(0);
		
		Calendar c1 = Calendar.getInstance();
		c1.add(Calendar.DAY_OF_MONTH, +30);
		Calendar c2 = Calendar.getInstance();
		date = c1.getTime();
		c1.setTime(date);
		c2.setTime(bookDate);
		
		
		
		System.out.println(date);
		System.out.println(bookDate);
		
		if (c1.get(Calendar.DAY_OF_YEAR) == (c2.get(Calendar.DAY_OF_YEAR))) { System.out.println("MATCHES!!!!");}else {
			System.out.println("SUCK!!!");
		}
		
		return "redirect:/librarian/books";
	}
	
	@RequestMapping(value = "/advancedsearch", method = RequestMethod.GET)
	public String advancedSearch(Model model) throws Exception {
			Book book = new Book();
			model.addAttribute("book", book);
			model.addAttribute("genre", genreService.getAll());
			return "librarianadvancedsearch";
	}
	
	@RequestMapping(value = "/advancedsearch", method = RequestMethod.POST)
	public String advancedSearch(@ModelAttribute("book") Book book, BindingResult result, @RequestParam("genreId") Integer gid, Model model) throws Exception {
		
		Genre genre = genreService.getById(gid);
		book.setGenre(genre);
		
		List<Book> books = bookService.advancedSearch(book);
		
		model.addAttribute("books", books);
		

		
		return "librariansearchresult";
	}
	
	@RequestMapping(value = "/holders", method = RequestMethod.GET)
	public String showBookHolders(@RequestParam("id") int id, Model model) throws Exception {
			Book book = new Book();
			book = bookService.getBookById(id);
			BooksInUse bookInUse = new BooksInUse();
			List<BooksInUse> booksInUse = bookInUseService.getBooksInUseByBook(book);
			List<BooksInUse> booksInUseEx = new ArrayList<BooksInUse>();
			
			Date date = new Date();
			Calendar currentDate = Calendar.getInstance();
			Calendar endDate = Calendar.getInstance();
			currentDate.setTime(date);
			int difference;
			
			for (BooksInUse booksInUse2 : booksInUse) {
				
				endDate.setTime(booksInUse2.getReturnDate());
				difference = endDate.get(Calendar.DAY_OF_YEAR) - currentDate.get(Calendar.DAY_OF_YEAR);
				booksInUse2.setDaysToreturn(difference);
				System.out.println("The difference is: " + difference);
				booksInUseEx.add(booksInUse2);
			}
			
			model.addAttribute("book", book);
			model.addAttribute("booksInUse", booksInUseEx);
			
			
			
		return "librarianbookholders";
	}
	
	@RequestMapping(value = "/simplesearch", method=RequestMethod.GET)
	public String simpleSearch(Model model) {
		return "librarianbooks";
	}
	
	@RequestMapping(value = "/simplesearch", method=RequestMethod.POST)
	public String simpleSearch(@RequestParam("request") String request, Model model) throws Exception {
		System.out.println("Search request: " + request);
		List<Book> books = bookService.simpleSearch(request);
		
		model.addAttribute("books", books);
		
		return "librariansearchresult";
	}
}
