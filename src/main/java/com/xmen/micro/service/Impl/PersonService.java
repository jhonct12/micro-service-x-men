package com.xmen.micro.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.xmen.micro.configuration.LoadProperties;
import com.xmen.micro.entity.Person;
import com.xmen.micro.entity.PersonsDNA;
import com.xmen.micro.mapper.exeption.DnaExistExeption;
import com.xmen.micro.repository.IPersonRespository;
import com.xmen.micro.service.IPersonService;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private IPersonRespository iPersonRespository;
	
	@Autowired
	private LoadProperties loadProperties;

	@Transactional(readOnly = true)
	@Override
	public Iterable<Person> findAll() {
		return iPersonRespository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Person> findById(Long id) {
		return iPersonRespository.findById(id);
	}


	@Transactional
	@Override
	public void deleteAll() {
		iPersonRespository.deleteAll();
	}

	@Transactional(readOnly = true)
	@Override
	public boolean checkDNAExist(ArrayList<String> dnas) {
		String md5Result = DigestUtils.md5DigestAsHex(dnas.toString().getBytes());

		Optional<Person> person = iPersonRespository.findByVerification(md5Result);

		return person.isPresent();

	}

	@Override
	public void saveNewDna(ArrayList<String> dnas, boolean mutant) {
		try {
			Person newPersonDna = new Person();

			List<PersonsDNA> listOfDnas = new ArrayList<>();

			dnas.forEach(item -> {
				PersonsDNA newA = new PersonsDNA();
				newA.setDna(item);
				listOfDnas.add(newA);
			});

			newPersonDna.setListDna(listOfDnas);
			newPersonDna.setMutant(mutant);

			String md5Result = DigestUtils.md5DigestAsHex(dnas.toString().getBytes());

			newPersonDna.setVerification(md5Result);

			iPersonRespository.save(newPersonDna);
		} catch (Exception e) {
			throw new DnaExistExeption(loadProperties.getMsgDnaExist());
		}

	}

}
