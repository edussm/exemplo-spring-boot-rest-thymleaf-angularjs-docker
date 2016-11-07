package com.igumnov.seedproject.service;

import java.util.Map;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@Secured({ "ROLE_demo" })
	@RequestMapping("/")
	public String home(Map<String, Object> model) {

		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@RequestMapping("/logout")
	public String logout() {
		return "login";
	}

	@RequestMapping("/ng-templates/{resource}")
	public String angularHtmlTemplate(@PathVariable("resource") String resource) {

		return "ng-templates/" + resource;
	}

}
