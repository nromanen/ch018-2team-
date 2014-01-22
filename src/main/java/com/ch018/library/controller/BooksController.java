package com.ch018.library.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
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
import com.ch018.library.helper.BookSearch;
import com.ch018.library.helper.Page;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        private final Logger logger = LoggerFactory.getLogger(BooksController.class);
        
        public BooksController() {
			
		}
        
        public BooksController(BookService bookService) {
        	
        	this.bookService = bookService;
        	
        }

        @RequestMapping(method = RequestMethod.GET)
        public String booksGeneral(Model model) {
        	Page page = bookService.getBooksComplex(new BookSearch());
            model.addAttribute("page", page);
            
            return "books";
        }

        @RequestMapping(value = "/search", method = RequestMethod.POST)
        public String booksSearch(@ModelAttribute BookSearch bookSearch, Model model) {
        	logger.info("bookSearch = {}", bookSearch);
            Page page = bookService.getBooksComplex(bookSearch);
            logger.info("page = {}", page);
            if (page.getBooks().isEmpty() || page.getBooks() == null) {
                model.addAttribute("nothing", true);
                model.addAttribute("query", bookSearch.getQuery());
            }
            model.addAttribute("page", page);
            return "books";
        }

        @RequestMapping(value = "/advancedSearch", method = RequestMethod.POST)
        public  String advancedSearch(@ModelAttribute BookSearch bookSearch, Model model) {
            logger.info("advanced search called with {}, {}, {}, {}", bookSearch);
            Page books = bookService.getBooksComplexByParams(bookSearch);
            if (books.getBooks().isEmpty() || books.getBooks() == null) {
                model.addAttribute("nothing", true);
            }
            model.addAttribute("page", books.getBooks());
            return "books";
        }


        @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
        @Secured( {"permitAll()"} )
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
