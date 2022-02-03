package com.sk.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping({ "/hello" })
	public String hello(@RequestAttribute("user") UserDetails user) {
		
		return user.getUsername();
	}

}
