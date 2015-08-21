package com.ooice.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope("prototype")
public class UserController {

	@RequestMapping("userList")
	public String userList(){
		
		System.out.println("123415465");
		
		return "user/userList";
	}
	
	
}
