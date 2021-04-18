package com.arp.SpringBootWithJSP.service;
import java.util.*;
import com.arp.SpringBootWithJSP.entity.*;
public interface StudentRegistrationService {

  void saveStudent(StudentRegistration studentRegistration);

  StudentRegistration findByMobile(String mobile);
	
  List<StudentRegistration> findAll();
  
  void deleteByStudentId(Long studentId);
  
  Optional<StudentRegistration> findById(Long studentId);
  
  Long countTotalStudent();
}
