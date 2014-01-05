package com.ch018.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ch018.library.entity.Book;
import com.ch018.library.entity.BooksInUse;
import com.ch018.library.entity.Genre;
import com.ch018.library.entity.Person;
import com.ch018.library.service.BookInUseService;
import com.ch018.library.service.BookService;

import com.ch018.library.service.GenreService;
import com.ch018.library.service.PersonService;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONObject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


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
    

    
    @RequestMapping(method = RequestMethod.GET)
    public String booksG(Model model){
        List<Genre> genres = genreService.getAll();
        List<Book> books = bookService.getAll();
        model.addAttribute("genres", genres);
        model.addAttribute("books", books);
        return "user/books";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public  String books(@RequestParam(value = "query") String query, HttpServletRequest request){
        
        List<Book> books;
        
        if(query.equals(""))
            books = bookService.getAll();
        else
            books = bookService.getBooksComplex(query);
        
        List<JSONObject> jsons = new ArrayList<>();
        
        if(request.isUserInRole("ROLE_USER")){
            
            for(Book book : books){
                JSONObject json = new JSONObject();
                json.put("bId", book.getbId());
                json.put("title", book.getTitle());
                json.put("authors", book.getAuthors());
                json.put("publisher", book.getPublisher());
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
                json.put("publisher", book.getPublisher());
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
        
        StringBuilder query = new StringBuilder("from Book where ");
        Map<String, String> params = new HashMap<>();
        if(genreId > 0){
            query.append("genre.id = :g ");
            params.put("g", genreId.toString());
        }else if(genreId <= 0){
            query.append("genre.id LIKE :gAll ");
            params.put("gAll", "%");
        }
        if(!title.equals("")){
            query.append("AND title LIKE :t ");
            params.put("t", "%" + title + "%");
        }
        if(!authors.equals("")){
            query.append("AND authors LIKE :a ");
            params.put("a", "%" + authors + "%");
        }
        if(!publisher.equals("")){
            query.append("AND publisher LIKE :p ");
            params.put("p", "%" + publisher + "%");
        }
        
        
        System.out.println(query);
        System.out.println(params);
        List<Book> books = bookService.getBooksComplexByParams(query.toString(), params);
        System.out.println(books);
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
    
    
    @RequestMapping(value = "/autocomplete", method = RequestMethod.GET)
    public @ResponseBody String autocomplete(@RequestParam("query") String query){
        List<String> titles = new ArrayList<>();
        
        List<Book> books = bookService.getBooksByTitle(query);
        for(Book book : books)
            titles.add(book.getTitle());

        JSONObject json = new JSONObject();
        json.put("suggestions", titles);
        
        return json.toString();
       
    }
    
    @RequestMapping(value = "/mybooks", method = RequestMethod.GET)
    public String myBooksG(Model model, Principal principal){
        Person person = personService.getByEmail(principal.getName());
        List<BooksInUse> uses = useService.getBooksInUseByPerson(person);
        model.addAttribute("uses", uses);
        return "user/myBooks";
    }

}
