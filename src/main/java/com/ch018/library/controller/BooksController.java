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
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
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
        public String booksG(Model model){
            List<Genre> genres = genreService.getAll();
            List<Book> books = bookService.getAll();
            model.addAttribute("genres", genres);
            model.addAttribute("books", books);

            return "books";
        }

        @RequestMapping(method = RequestMethod.POST)
        @ResponseBody
        public  String books(@RequestParam(value = "query") String query){

            return bookService.getBooksComplexAsJson(query).toString();    
        }

        @RequestMapping(value = "/advancedSearch", method = RequestMethod.POST)
        @Secured({"ROLE_USER"})
        public @ResponseBody String advancedSearch(@RequestParam("title") String title,
                                                    @RequestParam("authors") String authors,
                                                    @RequestParam("publisher") String publisher,
                                                    @RequestParam("genreId") Integer genreId,
                                                    HttpServletRequest request){
            logger.info("advanced search called with {}, {}, {}, {}", title, authors, publisher, genreId);
            return bookService.getBooksComplexByParamsAsJson(genreId, title, authors, publisher).toString();
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
