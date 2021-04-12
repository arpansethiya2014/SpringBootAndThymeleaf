package com.arp.SpringBootWithJSP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arp.SpringBootWithJSP.entity.StudentRegistration;
import com.arp.SpringBootWithJSP.repo.StudentRegistrationRepository;
import com.arp.SpringBootWithJSP.util.CurrentDate;


@Service("studentRegistrationService")
public class StudentRegistrationServiceImpl implements StudentRegistrationService{
	
	@Autowired
	private StudentRegistrationRepository studentRegistrationRepository;

	@Override
	public void saveStudent(StudentRegistration studentRegistration) {
		studentRegistration.setDate(CurrentDate.getCurrentDate());
		studentRegistrationRepository.save(studentRegistration);
	}

	@Override
	public StudentRegistration findByMobile(String mobile) {
	  return studentRegistrationRepository.findByMobile(mobile);
	}

	
	
}