/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ch018.library.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Edd Arazian
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Incorrect date")
public class IncorrectDate extends Exception{
    
    public IncorrectDate(String msg){
        super(msg);
    }
}
