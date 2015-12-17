package Voz.controller;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class VozController {
	@RequestMapping("/hello")
	public ModelAndView testPost() {
		String message = "TEST POST PLS IGNORE";
		return new ModelAndView("hello", "message", message);
	}
}
