package com.arp.SpringBootWithJSP.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class StudentController {

	@RequestMapping(value= {"/studenRegistration"}, method=RequestMethod.GET)
	 public ModelAndView studenRegistration() {
	  ModelAndView model = new ModelAndView();
	  
	  model.setViewName("student/studenRegistration");
	  return model;
	 }
	
}
