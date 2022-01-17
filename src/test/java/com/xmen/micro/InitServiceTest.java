package com.xmen.micro;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.xmen.micro.domain.StatsDNA;
import com.xmen.micro.entity.Person;
import com.xmen.micro.mapper.exeption.DnaExistExeption;
import com.xmen.micro.service.IInitStatsService;
import com.xmen.micro.service.IInitVerificationService;
import com.xmen.micro.service.IPersonService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InitServiceTest {

	@Autowired
	private IInitVerificationService iInitVerificationService;

	@Autowired
	private IInitStatsService iInitStatsService;
	
	@Autowired
	private IPersonService iPersonService;

	// bind the above RANDOM_PORT
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@DisplayName("startProcess input no valid")
	@Test
	@Order(1)
	void startProcessNoInput() {
		assertEquals(Boolean.FALSE, iInitVerificationService.startProcess(new ArrayList<>()));
	}

	@DisplayName("startProcess validate vertical and horizontal")
	@Test
	@Order(2)
	void validateHorizontalAndVertical() {
		assertEquals(Boolean.TRUE, iInitVerificationService.startProcess(Models.singleDNA().getDna()));
	}

	@DisplayName("startProcess dna exit")
	@Test
	@Order(3)
	void validateIfDnaExist() {

		try {
			assertEquals(Boolean.TRUE, iInitVerificationService.startProcess(Models.singleDNA().getDna()));
		} catch (DnaExistExeption e) {
			assertEquals("DNA already exist", e.getMessage());

		}
	}

	@DisplayName("startProcess validate verify Diagonal Left To Right")
	@Test
	@Order(4)
	void validateverifyDiagonalLeftToRight() {
		assertEquals(Boolean.TRUE, iInitVerificationService.startProcess(Models.leftToRight().getDna()));
	}

	@DisplayName("startProcess validate verify Diagonal Right To Left")
	@Test
	@Order(5)
	void validateverifyDiagonalRightToLeft() {
		assertEquals(Boolean.TRUE, iInitVerificationService.startProcess(Models.rightToLeft().getDna()));
	}

	@DisplayName("verify stats")
	@Test
	@Order(6)
	void getStats() {
		StatsDNA dna = iInitStatsService.getStats();
		assertEquals(0D, dna.getRatio());
	}
	
	@DisplayName("verify if person byId exist")
	@Test
	@Order(7)
	void findById() {
		Optional<Person> result = iPersonService.findById(1L);
		Assertions.assertNotNull(result.get());
	}

	@DisplayName("test principal controller")
	@Test
	@Order(8)
	public void mutant() throws MalformedURLException {

		ResponseEntity<String> response = restTemplate.postForEntity(
				new URL("http://localhost:" + port + "/mutant/").toString(), Models.singleDNA(), String.class);
		assertEquals("400 BAD_REQUEST", response.getStatusCode().toString());

	}
}
