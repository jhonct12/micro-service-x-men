package com.xmen.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xmen.micro.service.IInitStatsService;

@RestController
@RequestMapping("/stats/")
public class StatsController {

	@Autowired
	private IInitStatsService iInitStatsService;

	@GetMapping
	public ResponseEntity<?> stats() {

		return ResponseEntity.ok().body(iInitStatsService.getStats());
	}
}
