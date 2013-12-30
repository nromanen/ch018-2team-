package com.ch018.library.controller;

/**
*
* @author Dmitry Sankovsky
*/

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
	
	@RequestMapping(value = "/simplesearch", method=RequestMethod.GET)
	public String simpleSearch(Model model) {
		
		return "simplesearch";
	}
	
	@RequestMapping(value = "/simplesearch", method=RequestMethod.POST)
	public String simpleSearch(@RequestParam("request") String request) throws Exception {
		System.out.println("Search request: " + request);
		ArrayList<Book> allbooks = (ArrayList<Book>) bookService.getAll();
		ArrayList<Book> searchResult = new ArrayList<Book>();
		Book book = new Book();
		book = allbooks.get(0);
		if(book.getAuthors().equals(request)) {
			System.out.println("Author");
		}
		
		if(book.getTitle().equals(request)) { 
			System.out.println("Title");
		}
		
		return "redirect:/librarian/books";
	}
	
	//ToReturn*all time*(Unfinished)
	@RequestMapping(value = "/toreturn")
	public String toReturn(Model model) throws SQLException {
		
		List<Date> result = bookInUseService.getBooksInUseToReturnDate();
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
		}
		
		model.addAttribute("books", books);
		
		return "librarianbooks";
	}
	
	//ToIssue*all time*(Unfinished) 
	@RequestMapping(value = "/toissue")
	public String toIssue(Model model) throws SQLException {
		/**/
		List<Date> result = bookInUseService.getBooksInUseToIssue();
		List<BooksInUse> biu = new ArrayList<BooksInUse>();
		ArrayList<Book> books = new ArrayList<Book>();
		for (Date date: result) {
			biu = bookInUseService.getBooksInUseByIssueDate(date);
			System.out.println("The date is: " + date);
			for (BooksInUse booksInUse : biu) {
				books.add(booksInUse.getBook());
			}
		}
		
		for (Book book : books) {
			System.out.println("Book is: " + book.getAuthors() + " " +book.getTitle());
		}
		
		model.addAttribute("books", books);
		
		return "librarianbooks";
	}
	//Add book is not finished. But Dates work pretty good
	@RequestMapping(value = "/toissueinhour")
	public String toIssueInHour(Model model) throws SQLException {
		
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, +35);
		Date date = new Date();
		date = calendar.getTime();
		
		Calendar calendar1 = Calendar.getInstance();
		calendar1.add(Calendar.DAY_OF_YEAR, -1);
		Date currentDate = new Date();
		currentDate = calendar1.getTime();
		System.out.println("Current date is: " + currentDate);
		List<Date> result = bookInUseService.getBooksInUseToIssue();
		List<BooksInUse> biu = new ArrayList<BooksInUse>();
		for (Date dt : result) {
			biu = bookInUseService.getBooksInUseByIssueDate(dt);
			for (BooksInUse bkInus : biu) {
				System.out.println("Current book is: " + bkInus.getIssueDate());
				if (bkInus.getIssueDate().after(currentDate) && bkInus.getIssueDate().before(date)){
						System.out.println("BINGO!!!!!");
				}
			}
		}
		
		System.out.println("Next date is: " + date);
		System.out.println(date);
		return "redirect:/librarian/books";
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
			return "librarianaddbook";
	}
	
	@RequestMapping(value = "/advancedsearch", method = RequestMethod.POST)
	public String advancedSearch(@ModelAttribute("book") Book book, BindingResult result, @RequestParam("genreId") Integer gid) throws Exception {
		Genre genre = genreService.getById(gid);
		System.out.println("Genre Id = " + gid);
		book.setGenre(genre);
		System.out.println("Book title = " + book.getTitle());
		
		List<Book> filteredBooks = new ArrayList<Book>();
		List<Book> allBooks = bookService.getAll();
		
		
		for (Book bk : allBooks) {
		if (bk.getTitle().equals(book.getTitle())){ //Search By title
			filteredBooks.add(bk);
		}
		}
		
		if (filteredBooks.size() > 0) {
			allBooks.clear();
			allBooks.addAll(filteredBooks);
			filteredBooks.clear();
			System.out.println(allBooks.get(0) + "~~~~" + "Found by title");
		}else {
			System.out.println("Title is null!");
		}
		
		for (Book bk : allBooks) {
			if (bk.getAuthors().equals(book.getAuthors())){ //Search By Authors
				filteredBooks.add(bk);
		}
		}
			
		if (filteredBooks.size() > 0) {
			allBooks.clear();
			allBooks.addAll(filteredBooks);
			filteredBooks.clear();
			System.out.println(allBooks.get(0) + "~~~~" + "Found by authors");
		}else {
			System.out.println("Title is null!");
		}
		
		for (Book bk : allBooks) {
			if (bk.getGenre().equals(book.getGenre())){ //Search By Genre
				filteredBooks.add(bk);
		}
		}
			
		if (filteredBooks.size() > 0) {
			allBooks.clear();
			allBooks.addAll(filteredBooks);
			filteredBooks.clear();
			System.out.println(allBooks.get(0) + "~~~~" + "Found by genre");
		}else {
			System.out.println("Title is null!");
		}
		
		for (Book bk : allBooks) {
			if (bk.getYear() == (book.getYear())){ //Search By Year
				filteredBooks.add(bk);
		}
		}
			
		if (filteredBooks.size() > 0) {
			allBooks.clear();
			allBooks.addAll(filteredBooks);
			filteredBooks.clear();
			System.out.println(allBooks.get(0) + "~~~~" + "Found by year");
		}else {
			System.out.println("Title is null!");
		}
		
		for (Book bk : allBooks) {
			if (bk.getPages() == (book.getPages())){ //Search By Pages
				filteredBooks.add(bk);
		}
		}
			
		if (filteredBooks.size() > 0) {
			allBooks.clear();
			allBooks.addAll(filteredBooks);
			filteredBooks.clear();
			System.out.println(allBooks.get(0) + "~~~~" + "Found by pages");
		}else {
			System.out.println("Title is null!");
		}
		
		for (Book bk : allBooks) {
			if (bk.getPublisher().equals(book.getPages())){ //Search By Publisher
				filteredBooks.add(bk);
		}
		}
			
		if (filteredBooks.size() > 0) {
			allBooks.clear();
			allBooks.addAll(filteredBooks);
			filteredBooks.clear();
			System.out.println(allBooks.get(0) + "~~~~" + "Found by publisher");
		}else {
			System.out.println("Title is null!");
		}
		
		for (Book book2 : allBooks) {
			System.out.println(book2.getAuthors() + " " + book2.getTitle() + "THE END!");
		}
		return "redirect:/librarian/books";
	}
}
