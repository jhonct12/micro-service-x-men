package com.xmen.micro.service.Impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmen.micro.configuration.LoadProperties;
import com.xmen.micro.service.ISequenceContainService;
import com.xmen.micro.service.ISequenceProcessorService;

@Service
public class SequenceProcessorService implements ISequenceProcessorService {

	@Autowired
	private ISequenceContainService iSequenceContainService;

	@Autowired
	private LoadProperties loadProperties;

	@Override
	public int verifyHorizontal(ArrayList<String> dnas, int count) {

		// funcion inicial para validar de manera horizontal cuantas veces esta la
		// sequencia, como se observa, si la catidad ya es la esperada se rompe el ciclo
		for (String dna : dnas) {
			if (count < loadProperties.getNumberRepetitions()) {
				count = iSequenceContainService.isContain(dna, count);
			} else {
				break;
			}
		}
		return count;
	}

	@Override
	public int verifyVertical(ArrayList<String> dnas, int count) {

		// funcion para validar cuantas veces de manera vertical esta la secucia, como
		// se observa, si la catidad ya es la esperada se rompe el ciclo
		for (int i = 0; i < dnas.size(); i++) {
			if (count < loadProperties.getNumberRepetitions()) {
				String verticalDNA = "";
				for (String dna : dnas) {
					verticalDNA += dna.charAt(i);
				}
				count = iSequenceContainService.isContain(verticalDNA, count);
			} else {
				break;
			}
		}
		return count;
	}

	@Override
	public int verifyDiagonalLeftToRight(ArrayList<String> dnas, int count) {

		// funcion para validar cuantas veces de manera diagonal de izquierda a derecha
		// y de arriba abajo esta la
		// secuencia, como
		// se observa, si la catidad ya es la esperada se rompe el ciclo
		// first diagonal up to down
		for (int i = loadProperties.getNumberCharacters() - 1; i < dnas.size(); i++) {
			if (count < loadProperties.getNumberRepetitions()) {
				String newSequence = "";
				for (int j = 0; j < (i + 1); j++) {
					newSequence += dnas.get(j).charAt(i - j);
				}
				count = iSequenceContainService.isContain(newSequence, count);
			} else {
				break;
			}
		}

		// funcion para validar cuantas veces de manera diagonal de izquierda a derecha
		// y de abajo a arriba esta
		// la
		// secuencia, como
		// se observa, si la catidad ya es la esperada se rompe el ciclo
		// second diagonal down to up
		for (int i = loadProperties.getNumberCharacters() - 1; i < dnas.size() - 1; i++) {
			if (count < loadProperties.getNumberRepetitions()) {
				String newSequence = "";
				for (int j = 0; j < (i + 1); j++) {
					newSequence += dnas.get(dnas.size() - (j + 1)).charAt(dnas.size() - ((i + 1) - j));
				}
				count = iSequenceContainService.isContain(newSequence, count);
			} else {
				break;
			}
		}
		return count;
	}

	@Override
	public int verifyDiagonalRightToLeft(ArrayList<String> dnas, int count) {

		// funcion para validar cuantas veces de manera diagonal de derecha a izquierda
		// y de arriba a abajo esta
		// la
		// secuencia, como
		// se observa, si la catidad ya es la esperada se rompe el ciclo
		// first diagonal up to down
		for (int i = loadProperties.getNumberCharacters() - 1; i < dnas.size(); i++) {
			if (count < loadProperties.getNumberRepetitions()) {
				String newSequence = "";
				for (int j = 0; j < (i + 1); j++) {
					newSequence += dnas.get(j).charAt(dnas.size() - ((i + 1) - j));
				}
				count = iSequenceContainService.isContain(newSequence, count);
			} else {
				break;
			}
		}

		// funcion para validar cuantas veces de manera diagonal de derecha a izquierda
		// y de abajo a arriba esta
		// la
		// secuencia, como
		// se observa, si la catidad ya es la esperada se rompe el ciclo
		// second diagonal down to up
		for (int i = loadProperties.getNumberCharacters() - 1; i < dnas.size() - 1; i++) {
			if (count < loadProperties.getNumberRepetitions()) {
				String newSequence = "";
				for (int j = 0; j < (i + 1); j++) {
					newSequence += dnas.get(dnas.size() - (j + 1)).charAt(i - j);
				}
				count = iSequenceContainService.isContain(newSequence, count);
			} else {
				break;
			}
		}

		return count;
	}
}
