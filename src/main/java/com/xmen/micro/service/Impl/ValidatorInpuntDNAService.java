package com.xmen.micro.service.Impl;

import java.util.ArrayList;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmen.micro.configuration.LoadProperties;
import com.xmen.micro.mapper.exeption.InvalidLetterExeption;
import com.xmen.micro.mapper.exeption.InvalidSequenceStructureExeption;
import com.xmen.micro.service.IValidatorInputDNAService;

@Service
public class ValidatorInpuntDNAService implements IValidatorInputDNAService {

	@Autowired
	private LoadProperties loadProperties;

	@Override
	public boolean validateInputDNA(ArrayList<String> dnas) {

		for (int i = 0; i < dnas.size(); i++) {
			// validate if all sequence is valid and same size
			if (dnas.get(i).length() != dnas.size()) {
				throw new InvalidSequenceStructureExeption(loadProperties.getMsgInvalidSequenceStructure());
			}

			// validate if letters are valid
			if (!Pattern.compile(loadProperties.getMutanAllowedLetters(), Pattern.CANON_EQ).matcher(dnas.get(i))
					.matches()) {
				throw new InvalidLetterExeption(loadProperties.getMsgInvalidLetters());
			}
		}

		// este caso es espacial, ya que validado lo anterior, pero la longitud es
		// inferior a la catidad de la longitud de la secuencia, la persona es
		// considerada humano
		if (dnas.size() < loadProperties.getNumberCharacters()) {
			// throw new
			// InvalidInputStructure(loadProperties.getMsgInvalidInputStructure());
			return false;
		}

		return true;
	}

}
