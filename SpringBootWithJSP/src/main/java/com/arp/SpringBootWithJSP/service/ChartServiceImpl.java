package com.arp.SpringBootWithJSP.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arp.SpringBootWithJSP.repo.StudentRegistrationRepository;

@Service
public class ChartServiceImpl implements ChartService{

	@Autowired
	private StudentRegistrationRepository studentRegistrationRepository;
	
	@Override
	public Map<String,Integer> yearData(String year) {
		List<?> list = studentRegistrationRepository.yearData(year);
		Map<String, Integer> graphData = new HashMap<>();
		try {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Integer totalStudent = 0;
				String month = null;
				Object[] object = (Object[]) it.next();
				BigInteger big = (BigInteger) object[0];
				totalStudent = big.intValue();
				month = (String) object[1];
				graphData.put(month, totalStudent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graphData;
	}

	@Override
	public Map<String,Integer> monthlyData(Integer monthValue,String year) {
		List<?> list = studentRegistrationRepository.monthData(monthValue, year);
		Map<String, Integer> graphData = new HashMap<>();
		try {
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Integer totalStudent = 0;
				String month = null;
				Integer day = null;
				Object[] object = (Object[]) it.next();
				BigInteger bigTotalSt = (BigInteger) object[0];
				totalStudent = bigTotalSt.intValue();
				day = (Integer) object[1];
				month = (String) object[2];
				month = String.valueOf(day)+"/" +month;
				graphData.put(month, totalStudent);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return graphData;
	}

	
	
}
