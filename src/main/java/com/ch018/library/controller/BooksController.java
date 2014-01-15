package com.ch018.library.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
/**
 * 
 * @author Edd Arazian
 *
 */

@Controller
@RequestMapping(value = "/books")
public class BooksController {


        @Autowired
        BookService bookService;
        @Autowired
        GenreService genreService;
        @Autowired
        PersonService personService;
        @Autowired
        BookInUseService useService;

        final Logger logger = LoggerFactory.getLogger(BooksController.class);

        @RequestMapping(method = RequestMethod.GET)
        public String booksGeneral(Model model){
            List<Book> books = bookService.getAll();
            model.addAttribute("books", books);

            return "books";
        }

        @RequestMapping(value = "/search", method = RequestMethod.POST)
        public String booksSearch(@ModelAttribute BookSearch bookSearch, Model model){

            Page books = bookService.getBooksComplex(bookSearch);
            if (books.getBooks().isEmpty() || books.getBooks() == null) {
                model.addAttribute("nothing", true);
                model.addAttribute("query", bookSearch.getQuery());
            }
            model.addAttribute("books", books.getBooks());
            return "books";
        }

        @RequestMapping(value = "/advancedSearch", method = RequestMethod.POST)
        @Secured({"ROLE_USER"})
        public  String advancedSearch(@ModelAttribute BookSearch bookSearch, Model model){
            logger.info("advanced search called with {}, {}, {}, {}", bookSearch);
            Page books = bookService.getBooksComplexByParams(bookSearch);
            if (books.getBooks().isEmpty() || books.getBooks() == null)
            model.addAttribute("books", books.getBooks());
            return "books";
        }


        @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
        public @ResponseBody String autocomplete(@RequestParam("query") String query){
            List<String> titles = new ArrayList<>();    
            List<Book> books = bookService.getBooksByTitle(query);
            for(Book book : books)
                titles.add(book.getTitle());
            JSONObject json = new JSONObject();
            json.put("suggestions", titles);
            logger.info("autocomplete called with {}", query);
            return json.toString();
        }

        @RequestMapping(value = "/mybooks", method = RequestMethod.GET)
        public String myBooks(Model model, Principal principal){
            Person person = personService.getByEmail(principal.getName());
            List<BooksInUse> uses = useService.getBooksInUseByPerson(person);
            model.addAttribute("uses", uses);
            return "mybooks";
        }

}
