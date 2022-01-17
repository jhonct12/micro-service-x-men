package com.xmen.micro.service;

import java.util.ArrayList;
import java.util.Optional;

import com.xmen.micro.entity.Person;

public interface IPersonService {

	public Iterable<Person> findAll();

	public Optional<Person> findById(Long id);
	
	public void deleteAll();

	boolean checkDNAExist(ArrayList<String> dnas);

	void saveNewDna(ArrayList<String> dnas, boolean mutant);
}
