package com.ch018.library.controller;


import com.ch018.library.DAO.BookDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.OrdersService;
import com.ch018.library.service.PaginationService;
import com.ch018.library.util.SearchParamsBook;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/librarian/books")
public class LibrarianBooksController {
		
		@Autowired
		private BookService bookService;

        @Autowired
        private OrdersService ordersService;
		
		@Autowired
		private GenreService genreService;
		
		@Autowired
		private BookInUseService bookInUseService;
		
		@Autowired
		private SearchParamsBook searchParams;
		
		@Autowired
		private PaginationService<Book> paginationService;
		
		final Logger logger = LoggerFactory.getLogger(LibrarianBooksController.class);
		
		
		@RequestMapping(value = "", method = RequestMethod.GET)
		public String bookList(@ModelAttribute SearchParamsBook tmpSearchParams, Model model) {
			
			searchParams.setMainFieldsDefault();
			
			List<Book> books = paginationService.getPaginatedResult(searchParams, tmpSearchParams, Book.class);
			
			model.addAttribute("books", books);
			
			return "librarian_books";
		}
		
		@RequestMapping(value = "/search", method = RequestMethod.GET)
        public String bookSearchGet(@ModelAttribute SearchParamsBook tmpParams, Model model) {
        	
        	List<Book> books;
        	
        	if(searchParams.isMainFieldsEmpty()) {
        		searchParams.setMainFieldsDefault();
        	}
        	
        	books = paginationService.getPaginatedResult(searchParams, tmpParams, Book.class);

            model.addAttribute("books", books);
            return "librarian_books";
        }
		
		
		
		@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	        public String add(Model model) throws Exception {
				Book book = new Book();
	
				model.addAttribute("book", book);
				model.addAttribute("genres", genreService.getAll());
				return "librarian_books_add_book";
		}
		
		@RequestMapping(value = "/addbook", method = RequestMethod.POST)
		public ResponseEntity<String> add(@Valid @ModelAttribute Book book, BindingResult result, HttpServletRequest request,
							@RequestParam("gid") Integer gid, @RequestParam("file") MultipartFile file, Model model) {
			
			book.setGenre(genreService.getById(gid));
			if (result.hasErrors()) {
				logger.info("Error Addind Book" + result.toString());
				return new ResponseEntity<String>(result.toString(), HttpStatus.BAD_REQUEST);
			} else {
				book.setCurrentQuantity(book.getGeneralQuantity());
				if(file != null || !file.isEmpty()) {
					saveFile(file, book, request);
				} else {
					book.setImg("resources/img/default.jpg");
				}
				bookService.save(book);
			}
			return new ResponseEntity<>("{}", HttpStatus.OK);
		}
		
		
		@RequestMapping(value = "/deletebook")
		public String deleteBook(@RequestParam("id") int bookId, Model model) {
			
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
		public String edit(@RequestParam("id") int bookId, Model model) {
			
			model.addAttribute("book", bookService.getBookById(bookId));
			model.addAttribute("genres", genreService.getAll());
			return "librarian_books_edit_book";
		}
		
		@RequestMapping(value = "/editbook", method = RequestMethod.POST)
		public String edit(@RequestParam("gid") Integer gid, @Valid @ModelAttribute  Book book, BindingResult result,
						    Model model) throws Exception {
			if (result.hasErrors()) {
				model.addAttribute("genres", genreService.getAll());
				logger.info("Error editing book: " + result.toString());
				return "librarian_books_edit_book";
			} else {
				bookService.update(book, gid);
			}
			return "redirect:/librarian/books";
		}
		
		@RequestMapping(value = "/advancedsearch", method = RequestMethod.GET)
		public String advancedSearch(Model model) throws Exception {
			Book book = new Book();
			model.addAttribute("book", book);
			model.addAttribute("genre", genreService.getAll());
			return "librarian_books_advanced_search";
		}
		
		@RequestMapping(value = "/advancedsearch", method = RequestMethod.POST)
		public String advancedSearch(@ModelAttribute("book") Book book, BindingResult result, @RequestParam("genreId") Integer gid, Model model) {
			
			List<Book> booksList = bookService.advancedSearch(book);
			
			if (booksList.size() > 0) {
				model.addAttribute("books", booksList);
			} else {
				model.addAttribute("books", bookService.getAll());
			}
			
			return "librarian_books";
		}
		
		@RequestMapping(value = "/holders", method = RequestMethod.GET)
		public String showBookHolders(@RequestParam("id") int id, Model model) {
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
		public String simpleSearch(@RequestParam("request") String request, Model model) {
			
			if (request.equals("")) {
				model.addAttribute("books", bookService.getAll());
				return "librarian_books";
			}
			
			List<Book> booksList = bookService.simpleSearch(request);
			
			if (booksList.size() > 0) {
				model.addAttribute("books", booksList);
			} else {
				model.addAttribute("books", bookService.getAll());
			}
			
			return "librarian_books";
		}
		
		
		private void saveFile(MultipartFile file, Book book, HttpServletRequest request) {
			BufferedImage image = null;
			String path = request.getSession().getServletContext().getRealPath("/");
			path = path + "resources\\img\\";
			try {
				image = ImageIO.read(file.getInputStream());
				String originalName = file.getOriginalFilename();
				String onlyName = originalName.substring(0, originalName.length() - 4);
				ImageIO.write(image, "jpg",new File(path + onlyName + ".jpg"));
				ImageIO.write(image, "gif",new File(path + onlyName + ".gif"));
				book.setImg("resources/img/" + onlyName + ".jpg");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		
    	@RequestMapping(value = "/getOrdersHolders", method = RequestMethod.POST)
    	public ResponseEntity<String> getOrdersHolders(@RequestParam("id") Integer bid) {
    		
    		Book book = bookService.getBookById(bid);
    		
    		long holdersCount = bookInUseService.getBookInUseCount(book);
    		long ordersCount = ordersService.getOrdersCount(book);
    		
    		JSONObject json = new JSONObject();
    		json.put("holders", holdersCount);
    		json.put("orders", ordersCount);
    		
    		return new ResponseEntity<>(json.toString(), HttpStatus.OK);
    	}
    
    
}
