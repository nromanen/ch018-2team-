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
import com.ch018.library.helper.AdvancedSearchQuery;
import com.ch018.library.helper.BookSearch;
import com.ch018.library.helper.Page;
import com.ch018.library.helper.PageContainer;
import com.ch018.library.helper.SearchParams;
import com.ch018.library.helper.SimpleSearchQuery;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
import com.sun.org.apache.regexp.internal.recompile;

import org.springframework.context.annotation.Scope;
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
        
        @Autowired
        private SimpleSearchQuery searchQuery;
       // @Autowired
        private AdvancedSearchQuery advancedSearchQuery;
        @Autowired
        private SearchParams searchParams;
        @Autowired
        private PageContainer pageContainer;

        private final Logger logger = LoggerFactory.getLogger(BooksController.class);
        
        @RequestMapping(method = RequestMethod.GET)
        public String booksGeneral(Model model) {
        	logger.info("searchParamsStart: {}", searchParams);
        	logger.info("SearchQueryStart: {}", searchQuery);
        	Page page;
        	searchParams.setDefaults();
        	pageContainer.recalculate(searchQuery, searchParams);
        	page = pageContainer.getPage(searchQuery, searchParams);
            model.addAttribute("page", page);
            
            return "books";
        }

        @RequestMapping(value = "/search", method = RequestMethod.POST)
        public String booksSearch(@ModelAttribute SimpleSearchQuery tmpQuery,
        							@ModelAttribute SearchParams tmpParams, Model model) {
        	logger.info("tmpParams = {}", tmpParams);
        	logger.info("searchParams = {}", searchParams);
        	logger.info("searchQuery = {}", searchQuery);
        	Page page;
        	if(!tmpQuery.equals(searchQuery) || !tmpParams.equals(searchParams)) {
        		
        		searchQuery.set(tmpQuery);
        		searchParams.set(tmpParams);
        		logger.info("searchParams = {}", searchParams);
            	logger.info("searchQuery = {}", searchQuery);
        		pageContainer.recalculate(searchQuery, searchParams);
        		page = pageContainer.getPage(searchQuery, searchParams);
        		
        	} else {
        		searchParams.set(tmpParams);
        		
        		page = pageContainer.getPage(searchQuery, searchParams);
        	}
        	if (page.getBooks().isEmpty() || page.getBooks() == null) {
                model.addAttribute("nothing", true);
                model.addAttribute("query", searchQuery.getQuery());
            }
            model.addAttribute("page", page);
            return "books";
        }

        @RequestMapping(value = "/advancedSearch", method = RequestMethod.POST)
        public  String advancedSearch(@ModelAttribute BookSearch bookSearch, Model model) {
            /*logger.info("advanced search called with {}, {}, {}, {}", bookSearch);
            //Page page = bookService.getBooksComplexByParams(bookSearch);
            if (page.getBooks().isEmpty() || page.getBooks() == null) {
                model.addAttribute("nothing", true);
            }
            model.addAttribute("page", page);*/
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
