package com.xmen.micro.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LoadProperties {

	@Value("${base.allowed.letters}")
	private String mutanAllowedLetters;

	@Value("${base.mutant.sequence}")
	private String mutanSequence;

	@Value("${base.number.characters:4}")
	private int numberCharacters;

	@Value("${base.number.repetitions:2}")
	private int numberRepetitions;

	@Value("${base.msg.dna.exist}")
	private String msgDnaExist;

	@Value("${base.msg.invalid.sequence.structure}")
	private String msgInvalidSequenceStructure;

	@Value("${base.msg.invalid.letters}")
	private String msgInvalidLetters;

	@Value("${base.msg.invalid.input.structure}")
	private String msgInvalidInputStructure;

	public List<String> getMutanSequence() {
		return new ArrayList<String>(Arrays.asList(mutanSequence.split(";")));
	}

	public String getMutanAllowedLetters() {
		return mutanAllowedLetters;
	}

	public int getNumberCharacters() {
		if (numberCharacters < 0) {
			numberCharacters = 1;
		}
		return numberCharacters;
	}

	public int getNumberRepetitions() {
		if (numberRepetitions < 0) {
			numberRepetitions = 1;
		}
		return numberRepetitions;
	}

	public String getMsgDnaExist() {
		return msgDnaExist;
	}

	public String getMsgInvalidSequenceStructure() {
		return msgInvalidSequenceStructure;
	}

	public String getMsgInvalidLetters() {
		return msgInvalidLetters;
	}

	public String getMsgInvalidInputStructure() {
		return msgInvalidInputStructure;
	}

}
