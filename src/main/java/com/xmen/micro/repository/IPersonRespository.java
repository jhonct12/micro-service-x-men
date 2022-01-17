package com.xmen.micro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.xmen.micro.entity.Person;

public interface IPersonRespository extends CrudRepository<Person, Long> {

	Optional<Person> findByVerification(String verification);

	@Query("select COUNT(*) from Person p where p.isMutant = true")
	int findCountMutant();
	
	@Query("select COUNT(*) from Person p where p.isMutant = false")
	int findCountHuman();
}
