package com.arp.SpringBootWithJSP.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.arp.SpringBootWithJSP.entity.StudentRegistration;
import com.arp.SpringBootWithJSP.repo.StudentRegistrationRepository;
import com.arp.SpringBootWithJSP.util.CurrentDate;
import com.arp.SpringBootWithJSP.util.SendMail;

@Service("studentRegistrationService")
public class StudentRegistrationServiceImpl implements StudentRegistrationService {

	@Autowired
	private StudentRegistrationRepository studentRegistrationRepository;

	@Value("${spring.mail.username}")
	private String userName;
	@Value("${spring.mail.password}")
	private String password;
	
	@Override
	public void saveStudent(StudentRegistration studentRegistration) {
		studentRegistration.setDate(CurrentDate.getCurrentDate());
		//SendMail.send(userName,password,studentRegistration);
		studentRegistrationRepository.save(studentRegistration);
	}

	@Override
	public StudentRegistration findByMobile(String mobile) {
		return studentRegistrationRepository.findByMobile(mobile);
	}

	@Override
	public List<StudentRegistration> findAll() {
		return studentRegistrationRepository.findAll();
	}

	@Override
	public void deleteByStudentId(Long studentId) {
		studentRegistrationRepository.deleteById(studentId);
	}

	@Override
	public Optional<StudentRegistration> findById(Long studentId) {
		return studentRegistrationRepository.findById(studentId);
	}

}
