package com.ch018.library.controller;

import java.util.List;
import java.util.Locale;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Genre;
import com.ch018.library.exceptions.EmptyGenreException;
import com.ch018.library.exceptions.GenreAlreadyExists;
import com.ch018.library.service.GenreService;

@Controller
@RequestMapping(value = "/librarian/genres")
public class LibrarianGenresController {

	@Autowired
	private GenreService genreService;
	

	
	private final String ENG = "en";
	private final String UKR = "ua";
	
	@RequestMapping(value = "")
	public String allGenres(Model model) {
		
		List<Genre> genres = genreService.getAll();

		model.addAttribute("genres", genres);
		
		return "librarian_genres";
	}
	
	@RequestMapping(value = "/addgenre", method = RequestMethod.GET)
	public String addGenre() {
		return "librarian_add_genre";
	}
	
	@RequestMapping(value = "/addgenre", method = RequestMethod.POST)
	public ResponseEntity<String> addGenre(@RequestParam( value = "ukr", required = false) String ukr
								, @RequestParam(value = "eng", required = false) String eng){
		
		if((ukr == null && eng == null) || (ukr.equals("") && eng.equals("")))
			return new ResponseEntity<String>("genre values are empty", HttpStatus.BAD_REQUEST);
		try {
			genreService.addGenre(eng, ukr);
			
			return new ResponseEntity<>("{}", HttpStatus.OK);
		} catch (Exception e) {
			if(e instanceof EmptyGenreException || e instanceof GenreAlreadyExists)
				return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGenre(@RequestParam("id") int id, Model model) throws Exception {
		
		Genre genre = genreService.getById(id);
		genreService.delete(genre);
		
		return "redirect:/librarian/genres";
	}
	
	

	
}
