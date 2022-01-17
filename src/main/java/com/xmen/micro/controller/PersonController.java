package com.xmen.micro.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xmen.micro.entity.Person;
import com.xmen.micro.service.IPersonService;

@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private IPersonService iPersonService;

	// Controlador que devuelve todos los dna analizados
	@GetMapping
	public ResponseEntity<?> list() {
		return ResponseEntity.ok().body(iPersonService.findAll());
	}

	// Controldor que devuelve un dna especifico 
	@GetMapping("/{id}")
	public ResponseEntity<?> byId(@PathVariable Long id) {

		Optional<Person> result = iPersonService.findById(id);

		if (!result.isPresent()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(result);
		}
	}
	
	// Eliminacion de todos los dna analizados
	@DeleteMapping
	public ResponseEntity<?> delete() {
		iPersonService.deleteAll();
		return ResponseEntity.noContent().build();
	}
}