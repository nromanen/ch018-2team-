package com.ch018.library.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;
import com.ch018.library.helper.BookSearch;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.ModelAttribute;
/**
 * 
 * @author Edd Arazian
 *
 */

@Controller
@RequestMapping(value = "/books")
public class BooksController {
    
        private final static String SORT = "authors";
        private final static Boolean ORDER = false;
        private final static Integer PAGE_NUM = 1;
        private final static Integer AMOUNT = 10;
        

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
        public String booksG(Model model){ // TODO: rename or explain what G means in comment
            List<Book> books = bookService.getAll();
            model.addAttribute("books", books);

            return "books";
        }

        @RequestMapping(method = RequestMethod.POST)
        public @ResponseBody String books(@ModelAttribute BookSearch bookSearch){

            return bookService.getBooksComplexAsJson(bookSearch).toString();    
        }

        @RequestMapping(value = "/advancedSearch", method = RequestMethod.POST)
        @Secured({"ROLE_USER"})
        public @ResponseBody String advancedSearch(@ModelAttribute BookSearch bookSearch){
            System.out.println(bookSearch);
            logger.info("advanced search called with {}, {}, {}, {}", bookSearch);
            return bookService.getBooksComplexByParamsAsJson(bookSearch).toString();
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
        public String myBooksG(Model model, Principal principal){
            Person person = personService.getByEmail(principal.getName());
            List<BooksInUse> uses = useService.getBooksInUseByPerson(person);
            model.addAttribute("uses", uses);
            return "mybooks";
        }

}
