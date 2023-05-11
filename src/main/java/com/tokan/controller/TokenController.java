package com.tokan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {
	
	
	@GetMapping("/qwe")
	public String welcome() {
		String text = "this is your home page";
		text+="this is not page aollow";
		return text;
	}

}
