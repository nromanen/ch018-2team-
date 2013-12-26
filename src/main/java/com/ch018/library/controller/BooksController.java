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
import com.ch018.library.service.BookService;

import com.ch018.library.service.GenreService;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.access.annotation.Secured;
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
    
    //JSON PART
    
    @RequestMapping(method = RequestMethod.GET)
    public String booksG(){
        return "books";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public  String books(){
        List<Book> books = bookService.getAll();
        List<JSONObject> jsons = new ArrayList<>();
        
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
        
        
        return jsons.toString();
    }
    
    
    
    //OLD PART
    
    /*    @RequestMapping()
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
	
	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
        @Secured({"ROLE_USER"})
	public String addBook(Model model) {
                            System.out.println("TEST");
		          model.addAttribute("genres", gServ.getAll());
                          return "addBook";
	}
        
        @RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute Book book, BindingResult result, @RequestParam("genreId") Integer gid) {
                            
		          book.setGenre(gServ.getById(gid));
                          System.out.println(book);
                          try{
                          bServ.save(book);
                          }catch(Exception e){
                              System.out.println(e);
                          }
                          return "redirect:/books/addBook";
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
