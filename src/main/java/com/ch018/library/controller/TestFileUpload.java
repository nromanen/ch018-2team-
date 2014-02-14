package com.ch018.library.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class TestFileUpload {

	
	@RequestMapping(value = "/fileupload", method = RequestMethod.GET)
	public String fileGet() {
		return "fileup";
	}
	
	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
	public String fileUpload(@RequestParam("file") MultipartFile file) {
		String name = "testing";
		if(!file.isEmpty()) {
			System.out.println("file EXISTS " + file.getName());
			try {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(name + "-uploaded")));
                stream.write(bytes);
                stream.close();
                return "fileup";
            } catch (Exception e) {
                return "fileup";
            }
        } else {
            return "fileup";
        }
		
	}
	
	
}
