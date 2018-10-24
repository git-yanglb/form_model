package com.wy.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IconController {

	@GetMapping("/icons")
	public String iconPage(){
		return "/icon/icon";
	}
	
}
