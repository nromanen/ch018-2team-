/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller;


import com.ch018.library.service.GenreService;
import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;



/**
 *
 * @author Admin
 */

@Controller(value = "genres")
public class GenreController implements ViewPreparer{

    @Autowired
    GenreService genreService;

    @Override
    public void execute(Request rqst, AttributeContext ac) {
    
        ac.putAttribute("genres", new Attribute(genreService.getAll()));
    
    }
    
    
    
    
    
    
}
