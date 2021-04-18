package com.arp.SpringBootWithJSP.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arp.SpringBootWithJSP.service.ChartService;
import java.util.*;

@RestController
public class ChartsController {

	@Autowired
	private ChartService chartService;

	@GetMapping("/get-yearData")
	public ResponseEntity<Map<String, Integer>> getYearChart() {
		Map<String, Integer> graphData = new HashMap<>();
		graphData = chartService.yearData("2021");
		return new ResponseEntity<>(graphData, HttpStatus.OK);
	}

	@GetMapping("/get-monthData")
	public ResponseEntity<Map<String, Integer>> getMonthChart() {
		Map<String, Integer> graphData = new HashMap<>();
		graphData = chartService.monthlyData(4, "2021");
		return new ResponseEntity<>(graphData, HttpStatus.OK);
	}
}
