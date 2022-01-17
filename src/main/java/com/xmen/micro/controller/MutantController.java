package com.xmen.micro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xmen.micro.domain.SingleDNA;
import com.xmen.micro.service.IInitVerificationService;

@RestController
@RequestMapping("/mutant/")
public class MutantController {

	@Autowired
	private IInitVerificationService iInitVerificationService;

	@PostMapping
	public ResponseEntity<?> mutant(@RequestBody SingleDNA dna) {
		if (iInitVerificationService.startProcess(dna.getDna())) {
			System.out.println("the person is mutant");
			return ResponseEntity.ok().build();
		} else {
			System.out.println("the person is human");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
		}
	}
}
