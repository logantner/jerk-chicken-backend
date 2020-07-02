package com.jerk.chicken.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/server")
public class ServerController {

	@GetMapping()
	@ResponseBody
	public int isServerUp(){
		return 1;
	}
	
	
}
