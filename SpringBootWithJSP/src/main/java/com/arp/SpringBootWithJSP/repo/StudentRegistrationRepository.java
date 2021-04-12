package com.arp.SpringBootWithJSP.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arp.SpringBootWithJSP.entity.StudentRegistration;
@Repository("studentRegistrationRepository")
public interface StudentRegistrationRepository extends JpaRepository<StudentRegistration, Long>{

	@Query(value = "SELECT * FROM student_registration s WHERE s.mobile = ?1", nativeQuery = true)
	StudentRegistration findByMobile(String mobile);
	
}
