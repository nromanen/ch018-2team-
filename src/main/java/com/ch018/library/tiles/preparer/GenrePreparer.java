package com.ch018.library.tiles.preparer;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.ch018.library.service.GenreService;
import com.ch018.library.service.GenreTranslationService;
/**
 *
 * @author Edd Arazian
 */
@Component(value = "genres")
public class GenrePreparer implements ViewPreparer {

	    @Autowired
	    private GenreTranslationService genreService;
	    
	    @Override
	    public void execute(Request rqst, AttributeContext ac) {
	        ac.putAttribute("genres", new Attribute(genreService.getAllByLocale(LocaleContextHolder.getLocale().getLanguage())));
	    }
}
