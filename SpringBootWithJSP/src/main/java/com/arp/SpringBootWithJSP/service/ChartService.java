package com.arp.SpringBootWithJSP.service;
import java.util.*;

public interface ChartService {

	Map<String,Integer> yearData(String year);
	
	Map<String,Integer> monthlyData(Integer month,String year);
	
}
