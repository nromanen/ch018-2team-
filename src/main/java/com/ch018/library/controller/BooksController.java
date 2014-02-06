package com.ch018.library.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ch018.library.DAO.UniversalDao;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
import com.ch018.library.service.UniversalService;
import com.ch018.library.util.PageContainer;
import com.ch018.library.util.SearchParams;
import com.ch018.library.util.SearchParamsBook;
import com.ch018.library.util.Switch;
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
        private SearchParamsBook searchParams;
        
        @Autowired
        private Switch switcher;
        
        @Autowired
        private UniversalService<Book> universalService;
        
        @Autowired
        private PageContainer<Book> pageContainer;
        
        @Autowired
        MessageSource messageSource;


        private final Logger logger = LoggerFactory.getLogger(BooksController.class);
        
        
        @RequestMapping(method = RequestMethod.GET)
        public String booksGeneral(Model model) {
        	
        	System.out.println(messageSource.getMessage("message.ordersI", new Object[] {"new"}, "fail", Locale.ENGLISH));
            model.addAttribute("arrivals", bookService.getLastByField("arrivalDate", 4));
            model.addAttribute("populars", bookService.getLastByField("ordersQuantity", 4));
            logger.info("arrivals {}", bookService.getLastByField("arrivalDate", 4));
            return "home";
        }
        
        
        @RequestMapping(value = "/search", method = RequestMethod.GET)
        public String bookSearchGet(@ModelAttribute SearchParamsBook tmpParams, Model model) {
        	
        	List<Book> books = null;
        	
        	if(!searchParams.isInit()) {
        		searchParams.setDefaults();
        	}
        	
        	if(searchParams.isSlidersNull()) {
        		searchParams.init();
        	}
        	
        	if(switcher.getSwitcher()) {
        		if(searchParams.isOnlyPageChangedOrSize(tmpParams)) {
        			logger.info("only page");
        			searchParams.update(tmpParams);
        			books = pageContainer.getItemsPart(searchParams, Book.class);
        		} else if(searchParams.isSortingFieldsChanged(tmpParams)){
        			searchParams.update(tmpParams);
        			pageContainer.recalculateLocal(searchParams, Book.class);
        			books = pageContainer.getItemsPart(searchParams, Book.class);	
        		} else {
            			searchParams.update(tmpParams);
                		books = universalService.getPaginatedResult(searchParams, Book.class);
                		pageContainer.setItems(books);
                		books = pageContainer.getItemsPart(searchParams, Book.class);
        		}
        	} else {
        		searchParams.update(tmpParams);	
            	
            	books = universalService.getPaginatedResult(searchParams, Book.class);
        	}

        	if (books.isEmpty() || books == null) {
                model.addAttribute("nothing", true);
            }
            model.addAttribute("books", books);
            return "books";
        }

        @RequestMapping(value = "/search", method = RequestMethod.POST)
        public String booksSearch(@ModelAttribute SearchParamsBook tmpParams, Model model) {

        	List<Book> books = null;
        	
        	searchParams.update(tmpParams);
        	logger.info("searchParams before pag {}", searchParams);
        	books = universalService.getPaginatedResult(searchParams, Book.class);
        	
        	if(switcher.getSwitcher()) {
        		pageContainer.setItems(books);
        	}

        	if (books.isEmpty() || books == null) {
                model.addAttribute("nothing", true);
            }
            model.addAttribute("books", books);
            logger.info("searchParams after {}", searchParams);
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
