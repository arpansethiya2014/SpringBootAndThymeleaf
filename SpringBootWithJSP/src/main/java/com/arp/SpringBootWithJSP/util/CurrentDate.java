package com.arp.SpringBootWithJSP.util;
import java.sql.*;
public class CurrentDate {

	public static Date getCurrentDate() {
		long millis = System.currentTimeMillis();  
		java.sql.Date date = new java.sql.Date(millis);  
		System.out.println(date);
		return date;
	}
	
}
