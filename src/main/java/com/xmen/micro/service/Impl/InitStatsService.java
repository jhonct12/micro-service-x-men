package com.xmen.micro.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmen.micro.domain.StatsDNA;
import com.xmen.micro.repository.IPersonRespository;
import com.xmen.micro.service.IInitStatsService;

@Service
public class InitStatsService implements IInitStatsService {

	@Autowired
	private IPersonRespository iPersonRespository;

	@Override
	public StatsDNA getStats() {

		// conosultamos la informacion insertada con el fin de generar las estadisticas
		// y realizar el calculo respectivo

		int countMutant = iPersonRespository.findCountMutant();

		int countHuman = iPersonRespository.findCountHuman();

		StatsDNA statsDNA = new StatsDNA();
		statsDNA.setCount_mutant_dna(countMutant);
		statsDNA.setCount_human_dna(countHuman);
		statsDNA.setRatio(calculateRadio(countMutant, countHuman));

		return statsDNA;
	}

	private double calculateRadio(int mutant, int human) {
		if (mutant == 0 || human == 0) {
			return 0D;
		}

		if (human == 0) {
			human = 1;
		}

		return (mutant * 1D / human);
	}
}
