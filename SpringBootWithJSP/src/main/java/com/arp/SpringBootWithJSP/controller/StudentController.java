package com.arp.SpringBootWithJSP.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arp.SpringBootWithJSP.entity.StudentRegistration;
import com.arp.SpringBootWithJSP.entity.User;
import com.arp.SpringBootWithJSP.service.StudentRegistrationService;
import com.arp.SpringBootWithJSP.service.UserService;

@Controller
public class StudentController {

	@Autowired
	 private UserService userService;
	
	@Autowired
	 private StudentRegistrationService studentService;
	
	@RequestMapping(value= {"/home/studentRegistration"}, method=RequestMethod.GET)
	 public ModelAndView studenRegistration() {
	  ModelAndView model = new ModelAndView();
	  StudentRegistration studentRegistration = new StudentRegistration();
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User user = userService.findUserByEmail(auth.getName());
	  model.addObject("userName", user.getFirstname() + " " + user.getLastname());
	  model.addObject("studentRegistration", studentRegistration);
	  model.setViewName("home/studentRegistration");
	  return model;
	 }
	
	@RequestMapping(value= {"/home/studentRegistration"}, method=RequestMethod.POST)
	 public ModelAndView addStudent(@Valid StudentRegistration studentRegistration, BindingResult bindingResult) {
	  ModelAndView model = new ModelAndView();
	  Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	  User user = userService.findUserByEmail(auth.getName());
	  model.addObject("userName", user.getFirstname() + " " + user.getLastname());
	  StudentRegistration studentExists = studentService.findByMobile(studentRegistration.getMobile());
	  if(studentExists != null) {
	   bindingResult.rejectValue("mobile", "error.student", "This mobile already exists!");
	  }
	  if(bindingResult.hasErrors()) {
	   model.setViewName("home/studentRegistration");
	  } else {
	   studentService.saveStudent(studentRegistration);
	   model.addObject("msg", "Student has been registered successfully!");
	   model.addObject("student", new StudentRegistration());
	   model.setViewName("home/studentRegistration");
	  }
	  
	  return model;
	 }
	
}
