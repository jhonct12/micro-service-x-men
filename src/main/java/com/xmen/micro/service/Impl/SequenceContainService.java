package com.xmen.micro.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmen.micro.configuration.LoadProperties;
import com.xmen.micro.service.ISequenceContainService;

@Service
public class SequenceContainService implements ISequenceContainService {

	@Autowired
	private LoadProperties loadProperties;

	@Override
	public int isContain(String sequenceDNA, int count) {

		// esta funcion permite recorrer las sequencias que identifican a un mutante,
		// adicionalmente, dentro de la cadena comprobar cuantas veces esta la sequencia
		// de esta manera se optimiza el proceso
		for (String sequenceMutant : loadProperties.getMutanSequence()) {
			String auxSequenceDNA = String.valueOf(sequenceDNA);
			while (auxSequenceDNA.indexOf(sequenceMutant) > -1) {

				auxSequenceDNA = auxSequenceDNA.substring(
						auxSequenceDNA.indexOf(sequenceMutant) + sequenceMutant.length(), auxSequenceDNA.length());
				count++;

				// si ya se encontraron la cantidad igual o mayor a la solicitada retornamos
				// mutante, si ya encuentro mas de
				// las esperadas no es necesario seguir buscando
				if (count >= loadProperties.getNumberRepetitions()) {
					break;
				}
			}

			// si ya se encontraron la cantidad igual o mayor a la solicitada retornamos
			// mutante, si ya encuentro mas de
			// las esperadas no es necesario seguir buscando
			if (count >= loadProperties.getNumberRepetitions()) {
				break;
			}
		}
		return count;
	}

}
