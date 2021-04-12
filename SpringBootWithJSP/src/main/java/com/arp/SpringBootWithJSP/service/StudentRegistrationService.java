package com.arp.SpringBootWithJSP.service;

import com.arp.SpringBootWithJSP.entity.*;
public interface StudentRegistrationService {

  void saveStudent(StudentRegistration studentRegistration);

  StudentRegistration findByMobile(String mobile);
	
}
