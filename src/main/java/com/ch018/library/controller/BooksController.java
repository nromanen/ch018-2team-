package com.ch018.library.controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;

import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
import java.security.Principal;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeSet;
import javax.persistence.Cache;
import javax.servlet.http.HttpServletRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 
 * @author Yurik Mikhaletskiy
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
    
    //JSON PART
    
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
    public  String books(@RequestParam(value = "query") String query, HttpServletRequest request){
        String[] queries = query.split(" ");
        List<Book> books;
        Comparator<Book> comparator = new Comparator<Book>(){
            
            @Override
            public int compare(Book b1, Book b2){
                return b1.getTitle().compareTo(b2.getTitle());
            }
            
            
                    
        };
        
        if(query.equals(""))
            books = bookService.getAll();
        else
            books = bookService.getBooksComplex(comparator, queries);
        
        List<JSONObject> jsons = new ArrayList<>();
        
        if(request.isUserInRole("ROLE_USER")){
            
            for(Book book : books){
                JSONObject json = new JSONObject();
                json.put("bId", book.getbId());
                json.put("title", book.getTitle());
                json.put("authors", book.getAuthors());
                json.put("description", book.getDescription());
                json.put("generalQuantity", book.getGeneralQuantity());
                json.put("currentQuantity", book.getCurrentQuantity());
                json.put("img", book.getImg());
                jsons.add(json);
        }
        }else{
            for(Book book : books){
                JSONObject json = new JSONObject();
                
                json.put("title", book.getTitle());
                json.put("authors", book.getAuthors());
                json.put("description", book.getDescription());
                json.put("generalQuantity", book.getGeneralQuantity());
                json.put("currentQuantity", book.getCurrentQuantity());
                json.put("img", book.getImg());
                jsons.add(json);
        }
        }
        
        JSONObject finalJson = new JSONObject();
        finalJson.put("auth", request.isUserInRole("ROLE_USER"));
        finalJson.put("books", jsons);
        
        return finalJson.toString();
    }
    
    @RequestMapping(value = "/advancedSearch", method = RequestMethod.POST)
    public @ResponseBody String advancedSearch(@RequestParam("title") String title,
                                                @RequestParam("authors") String authors,
                                                @RequestParam("publisher") String publisher,
                                                @RequestParam("genreId") Integer genreId,
                                                HttpServletRequest request){
        Set<Book> books = new TreeSet<>(new Comparator<Book>() {

            @Override
            public int compare(Book b1, Book b2) {
                return b1.getTitle().compareTo(b2.getTitle());
            }
        });
        List<JSONObject> jsons = new ArrayList<>();
        /*List<Book> byTitle = new ArrayList<>();
        List<Book> byAuthors = new ArrayList<>();
        List<Book> byPublisher = new ArrayList<>();
        List<Book> byGenre = new ArrayList<>();*/
        if(!title.equals(""))
            books.addAll(bookService.getBooksByTitle(title));
        if(!authors.equals(""))
            books.addAll(bookService.getBooksByAuthors(authors));
        if(!authors.equals(""))
            books.addAll(bookService.getBooksByPublisher(publisher));
        if(genreId > 0)
            books.addAll(bookService.getBooksByGenre(genreService.getById(genreId)));
        
        if(request.isUserInRole("ROLE_USER")){
            
            for(Book book : books){
                JSONObject json = new JSONObject();
                json.put("bId", book.getbId());
                json.put("title", book.getTitle());
                json.put("authors", book.getAuthors());
                json.put("description", book.getDescription());
                json.put("generalQuantity", book.getGeneralQuantity());
                json.put("currentQuantity", book.getCurrentQuantity());
                json.put("img", book.getImg());
                jsons.add(json);
        }
        }else{
            for(Book book : books){
                JSONObject json = new JSONObject();
                
                json.put("title", book.getTitle());
                json.put("authors", book.getAuthors());
                json.put("description", book.getDescription());
                json.put("generalQuantity", book.getGeneralQuantity());
                json.put("currentQuantity", book.getCurrentQuantity());
                json.put("img", book.getImg());
                jsons.add(json);
        }
        }
        
        JSONObject finalJson = new JSONObject();
        finalJson.put("auth", request.isUserInRole("ROLE_USER"));
        finalJson.put("books", jsons);
        
        return finalJson.toString();
    }
    
    
    
  /*  @RequestMapping(value = "/advancedSearch/getGenres", method = RequestMethod.POST)
    public @ResponseBody String advancedSearchGenres(){
        List<Genre> genres = genreService.getAll();
        List<JSONObject> jsons = new ArrayList<>();
        for(Genre genre : genres){
            JSONObject json = new JSONObject();
            json.put("id", genre.getId());
            json.put("description", genre.getDescription());
            jsons.add(json);
        }
        
        return jsons.toString();
    }*/
    
    
    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
    public @ResponseBody String autocomplete(@RequestParam("query") String query){
        List<String> titles = new ArrayList<>();
        
        List<Book> books = bookService.getBooksByTitle(query);
        for(Book book : books)
            titles.add(book.getTitle());
        
        //List<JSONObject> jsons = new ArrayList<>();
        
        
        JSONObject json = new JSONObject();
        json.put("suggestions", titles);
        
        return json.toString();
       
    }
    
    @RequestMapping(value = "/mybooks", method = RequestMethod.POST)
    public @ResponseBody String myBooks(Principal principal){
        Person person = personService.getByEmail(principal.getName());
        List<BooksInUse> uses = useService.getBooksInUseByPerson(person);
        List<JSONObject> jsons = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        for(BooksInUse use : uses){
            JSONObject json = new JSONObject();
            json.put("title", use.getBook().getTitle());
            json.put("returnDate", use.getReturnDate());
            int days = (int) (use.getReturnDate().getTime() - new Date().getTime())/(1000 * 3600 * 24);
            json.put("days", days);
            jsons.add(json);
        }
        return jsons.toString();
    }
    
    //OLD PART
    
    /* 
    @RequestMapping(value = "/addBook", method = RequestMethod.GET)
        @Secured({"ROLE_USER"})
	public String addBook(Model model) {
                            System.out.println("TEST");
		          model.addAttribute("genres", genreService.getAll());
                          return "addBook";
	}
        
        @RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute Book book, BindingResult result, @RequestParam("genreId") Integer gid) {
                            
		          book.setGenre(genreService.getById(gid));
                          System.out.println(book);
                          try{
                          bookService.save(book);
                          }catch(Exception e){
                              System.out.println(e);
                          }
                          return "redirect:/books/addBook";
	}
    
       @RequestMapping()
        public String bookList(Model model){
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
            model.addAttribute("books", bServ.getAll());
            return "books";
        }
        
        @RequestMapping(value = "/search", method = RequestMethod.GET)
        public String search(@RequestParam("query") String query, Model model){
            model.addAttribute("books", bServ.getBooksComplex(query));
            return "books";
        }
	
	
        
        @RequestMapping(value = "/edit", method = RequestMethod.GET)
        public String editBook(@RequestParam("id") Integer id, Model model){
                Book book = bServ.getBookById(id);
                model.addAttribute("book", book);
                model.addAttribute("genres", gServ.getAll());
                return "editBook";
        }
        
        @RequestMapping(value = "/edit", method = RequestMethod.POST)
        public String editBook(@ModelAttribute Book book, BindingResult result, @RequestParam("genreId") Integer gid){
                book.setGenre(gServ.getById(gid));
                System.out.println(book);
                bServ.update(book);
                return "redirect:/books/";
                
        }*/
}
