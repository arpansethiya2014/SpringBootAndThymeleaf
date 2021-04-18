package com.arp.SpringBootWithJSP.repo;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arp.SpringBootWithJSP.entity.StudentRegistration;
@Repository("studentRegistrationRepository")
public interface StudentRegistrationRepository extends JpaRepository<StudentRegistration, Long>{

	@Query(value = "SELECT * FROM student_registration s WHERE s.mobile = ?1", nativeQuery = true)
	StudentRegistration findByMobile(String mobile);
	
	@Query(value = "select count(*) as cnt, monthname(s.date) as month from student_registration s where YEAR(s.date) = ?1 group by month(s.date)", nativeQuery = true)
	List<?> yearData(String year);
	
	@Query(value = "select count(*) as cnt, day(s.date) as day, monthname(s.date) as month from student_registration s   where month(s.date) = ?1 and year(s.date) = ?2 Group by day(s.date)" , nativeQuery = true)
	List<?> monthData(Integer month, String year);
}
