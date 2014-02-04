package com.ch018.library.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.helper.SearchParams;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
/**
 * 
 * @author Edd Arazian
 *
 */

@Controller
@RequestMapping(value = "/books")
public class BooksController {


        @Autowired
        private BookService bookService;
        
        @Autowired
        private GenreService genreService;
        
        @Autowired
        private PersonService personService;
        
        @Autowired
        private BookInUseService useService;
        
        @Autowired
        private SearchParams searchParams;


        private final Logger logger = LoggerFactory.getLogger(BooksController.class);
        
        
        @RequestMapping(method = RequestMethod.GET)
        public String booksGeneral(Model model) {
        	
        	
            model.addAttribute("arrivals", bookService.getLastByField("arrivalDate", 4));
            model.addAttribute("populars", bookService.getLastByField("ordersQuantity", 4));
            logger.info("arrivals {}", bookService.getLastByField("arrivalDate", 4));
            return "home";
        }
        
        
        @RequestMapping(value = "/search", method = RequestMethod.GET)
        public String bookSearchGet(@ModelAttribute SearchParams tmpParams, Model model) {
        	
        	List<Book> books;
        	
        	if(!searchParams.isInit()) {
        		searchParams.setDefaults();
        	}
        	
        	if(searchParams.isSlidersNull()) {
        		searchParams.init();
        	}
        	
        	searchParams.update(tmpParams);
        	
        	books = bookService.getBooksComplex();
        	
        	if (books.isEmpty() || books == null) {
                model.addAttribute("nothing", true);
            }
            model.addAttribute("books", books);
            return "books";
        }

        @RequestMapping(value = "/search", method = RequestMethod.POST)
        public String booksSearch(@ModelAttribute SearchParams tmpParams, Model model) {

        	searchParams.update(tmpParams);

        	List<Book> books = bookService.getBooksComplex();

        	if (books.isEmpty() || books == null) {
                model.addAttribute("nothing", true);
            }
            model.addAttribute("books", books);
            return "books";
        }


        @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
        public @ResponseBody String autocomplete(@RequestParam("query") String query) {
            List<String> titles = new ArrayList<>();   
            JSONObject json = new JSONObject();
            List<Book> books = bookService.getBooksByTitle(query);
            for (Book book : books) {
                titles.add(book.getTitle());
            }
            json.put("suggestions", titles);
            logger.info("autocomplete called with {}", query);
            return json.toString();
        }

        @RequestMapping(value = "/mybooks", method = RequestMethod.GET)
        public String myBooks(Model model, Principal principal) {
            Person person = personService.getByEmail(principal.getName());
            List<BooksInUse> uses = useService.getBooksInUseByPerson(person);
            model.addAttribute("uses", uses);
            return "mybooks";
        }

}
