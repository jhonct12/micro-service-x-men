package com.xmen.micro.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xmen.micro.dto.MessageError;
import com.xmen.micro.mapper.exeption.DnaExistExeption;
import com.xmen.micro.mapper.exeption.InvalidInputStructureExeption;
import com.xmen.micro.mapper.exeption.InvalidLetterExeption;
import com.xmen.micro.mapper.exeption.InvalidSequenceStructureExeption;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(InvalidInputStructureExeption.class)
	public ResponseEntity<?> customHandleInvalidInputStructureExeption(Exception ex) {

		MessageError error = new MessageError();
		error.setMensaje(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidSequenceStructureExeption.class)
	public ResponseEntity<?> customHandleInvalidSequenceStructureExeption(Exception ex) {

		MessageError error = new MessageError();
		error.setMensaje(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidLetterExeption.class)
	public ResponseEntity<?> customHandleInvalidLetterExeption(Exception ex) {

		MessageError error = new MessageError();
		error.setMensaje(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DnaExistExeption.class)
	public ResponseEntity<?> customHandleDnaExistExeption(Exception ex) {

		MessageError error = new MessageError();
		error.setMensaje(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
