package com.ch018.library.controller;

import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ch018.library.entity.Genre;
import com.ch018.library.entity.GenreTranslations;
import com.ch018.library.service.GenreService;
import com.ch018.library.service.GenreTranslationService;

@Controller
@RequestMapping(value = "/librarian/genres")
public class LibrarianGenresController {

	@Autowired
	private GenreService genreService;
	
	@Autowired
	private GenreTranslationService genreTranslationsServeice;
	
	private final String ENG = "en";
	private final String UKR = "ua";
	
	@RequestMapping(value = "")
	public String allGenres(Model model) {
		
		Locale locale = LocaleContextHolder.getLocale();
		
		List<Genre> genresByLocale = genreService.getAll();
		
		/*for (GenreTranslations genreTranslations : genresByLocale) {
			System.out.println("Result: " + genreTranslations.getGenreTranslation());
		}*/
		
		model.addAttribute("genres", genresByLocale);
		
		return "librarian_genres";
	}
	
	@RequestMapping(value = "/addgenre", method = RequestMethod.GET)
	public String addGenre(Model model) throws Exception {
		return "librarian_add_genre";
	}
	
	@RequestMapping(value = "/addgenre", method = RequestMethod.POST)
	public String addGenre(@RequestParam( value = "ukr", required = false) String ukr, @RequestParam("eng") String eng){
	
		//System.out.println("Result UKR: " + ukr);
		System.out.println("Result ENG: " + eng);
		/*int max = 1;
		Locale locale = LocaleContextHolder.getLocale();
		List<GenreTranslations> genres = genreTranslationsServeice.getAllByLocale(locale.toString());
		System.out.println(genres.size());
		if( genres.size() > 0) {
			int[] genresId = new int[genres.size()];
			int j = 0;
			for (GenreTranslations genresTransl : genres) {
				genresId[j] = genresTransl.getGenreId();
				j++;
			}
			
			max = genresId[0];
			for(int i = 1; i < genresId.length; i++){
				if(max < genresId[i]){
					max = genresId[i];
				}
			}
			
			max +=1;
			System.out.println("Max value is " + max);
		}
		
		GenreTranslations genreUKR = new GenreTranslations();
		
		genreUKR.setGenreId(max);
		genreUKR.setGenreTranslation(ukr);
		genreUKR.setLanguage(UKR);
		genreTranslationsServeice.save(genreUKR);

		GenreTranslations genreENG = new GenreTranslations();
		genreENG.setGenreId(max);
		genreENG.setGenreTranslation(eng);
		genreENG.setLanguage(ENG);
		genreTranslationsServeice.save(genreENG);*/
		
		Genre genre = new Genre();
		genre.setDescription(eng);
		genreService.save(genre);
		
		return "redirect:/librarian/genres";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteGenre(@RequestParam("id") int id, Model model) throws Exception {
		
		Genre genre = genreService.getById(id);
		genreService.delete(genre);
		
		return "redirect:/librarian/genres";
	}
}
