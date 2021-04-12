package com.arp.SpringBootWithJSP.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.arp.SpringBootWithJSP.entity.StudentRegistration;
import com.arp.SpringBootWithJSP.entity.User;
import com.arp.SpringBootWithJSP.service.StudentRegistrationService;
import com.arp.SpringBootWithJSP.service.UserService;
import java.util.*;

@Controller
public class StudentController {

	@Autowired
	private UserService userService;

	@Autowired
	private StudentRegistrationService studentService;

	@RequestMapping(value = { "/home/studentRegistration" }, method = RequestMethod.GET)
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

	@RequestMapping(value = { "/home/studentRegistration" }, method = RequestMethod.POST)
	public ModelAndView addStudent(@Valid StudentRegistration studentRegistration, BindingResult bindingResult) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		StudentRegistration studentExists = studentService.findByMobile(studentRegistration.getMobile());
		if (studentExists != null) {
			bindingResult.rejectValue("mobile", "error.student", "This mobile already exists!");
		}
		if (bindingResult.hasErrors()) {
			model.setViewName("home/studentRegistration");
		} else {
			studentService.saveStudent(studentRegistration);
			model.addObject("msg", "Student has been registered successfully!");
			model.addObject("student", new StudentRegistration());
			model.setViewName("home/studentRegistration");
		}

		return model;
	}

	@RequestMapping(value = { "/home/studentDetails" }, method = RequestMethod.GET)
	public ModelAndView studentDetails() {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		List<StudentRegistration> list = studentService.findAll();
		if (list.size() > 0) {
			model.addObject("student", list);
			model.setViewName("home/studentDetails");
		} else {
			model.addObject("msg", "Record Not Avilable!");
			model.setViewName("home/studentDetails");
		}
		return model;
	}
	
	@RequestMapping(value = { "/home/studentDetails/{studentId}" }, method = RequestMethod.GET)
	public ModelAndView deleteStudent(@PathVariable(value = "studentId") Long studentId) {
		System.out.println("Student Id : " + studentId);
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		model.addObject("userName", user.getFirstname() + " " + user.getLastname());
		studentService.deleteByStudentId(studentId);
		List<StudentRegistration> list = studentService.findAll();
		model.addObject("student", list);
		model.addObject("msg", "Remove Student Details!");
		model.setViewName("home/studentDetails");
	   return model;
	}
}
 